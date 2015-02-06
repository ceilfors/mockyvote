package org.mysociety.mockyvote.yournextmp

import groovy.util.logging.Slf4j
import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import org.mysociety.mockyvote.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

import javax.transaction.Transactional

/**
 * Created by on 25/01/15.
 */
@Service
@Slf4j
class YourNextMpVoteService implements MockyVoteService {

    @Autowired
    @Qualifier('mapit')
    RESTClient mapitRest

    @Autowired
    @Qualifier('yournextmp')
    RESTClient yournextmpRest

    @Autowired
    ConstituencyRepository constituencyRepository

    @Autowired
    CandidateRepository candidateRepository

    @Transactional
    @Override
    Constituency getConstituency(String postCode) {
        def constituencyData = getConstituencyData(postCode)
        def constituencyWmcId = constituencyData.shortcuts.WMC
        def name = constituencyData.areas."$constituencyWmcId".name
        log.info("Found constituency id [$constituencyWmcId], name [$name]")

        def constituency = constituencyRepository.findByName(name)
        if (constituency) {
            return constituency
        } else {
            def newConstituency = new Constituency()
            newConstituency.country = constituencyData.areas."$constituencyWmcId".country_name
            newConstituency.name = name
            newConstituency.candidates = createCandidatesFromResponseData(getCandidateData(constituencyWmcId))
            return constituencyRepository.save(newConstituency)
        }
    }

    @Override
    List<Constituency> getAllConstituencies() {
        constituencyRepository.findAll()
    }

    private getConstituencyData(String postCode) {
        log.info("Getting for constituency with post code: [$postCode]")
        try {
            def response = mapitRest.get(path: "postcode/$postCode")
            assert response.status == 200
            return response.data

        } catch (HttpResponseException ex) {
            if (ex.response.status == HttpStatus.NOT_FOUND) {
                throw new ConstituencyNotFoundException(postCode, ex)
            } else {
                throw ex
            }
        }
    }

    private static List<Candidate> createCandidatesFromResponseData(candidateData) {
        candidateData.result.memberships.person_id.findAll { it.standing_in["2015"] != null }.collect {
            log.debug("Creating candidate with name: ${it.name}")
            return new Candidate(name: it.name, vote: 0L)
        }
    }

    private getCandidateData(constituencyWmcId) {
        log.info("Getting for candidates in constituency: [$constituencyWmcId]")
        def response = yournextmpRest.get(path: "api/v0.1/posts/${constituencyWmcId}", query: [embed: "membership.person"])
        return response.data
    }

    @Override
    @Transactional
    Candidate vote(Long candidateId) {
        def candidate = candidateRepository.findOne(candidateId)
        candidate.vote++
        return candidateRepository.save(candidate)
    }

    @Transactional
    @Override
    Constituency notVoting(Long constituencyId) {
        def constituency = constituencyRepository.findOne(constituencyId)
        constituency.notVoting++
        return constituency
    }

    @Override
    Constituency get(Long constituencyId) {
        constituencyRepository.findOne(constituencyId)
    }
}
