package org.mysociety.mockyvote
/**
 * Created by on 25/01/15.
 */
interface MockyVoteService {

    Constituency getConstituency(String postCode)

    List<Constituency> getAllConstituencies()

    Candidate vote(Long candidateId)

    Constituency notVoting(Long constituencyId)

    Constituency get(Long constituencyId)
}