package org.mysociety.mockyvote

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * Created by on 25/01/15.
 */
@RestController
class MockyVoteRestController {

    @Autowired
    MockyVoteService mockyVoteService

    @RequestMapping(value = '/constituency', method = RequestMethod.GET)
    List<Constituency> constituencies() {
        mockyVoteService.allConstituencies
    }

    @RequestMapping(value = '/constituency/{id}/notvoting', method = RequestMethod.POST)
    Constituency notVoting(@PathVariable Long id) {
        mockyVoteService.notVoting(id)
    }

    @RequestMapping(value = '/constituency/postcode/{postCode}', method = RequestMethod.GET)
    Constituency constituencyByPostCode(@PathVariable String postCode) {
        mockyVoteService.getConstituency(postCode)
    }

    @RequestMapping(value = '/constituency/{id}', method = RequestMethod.GET)
    Constituency constituencyById(@PathVariable Long id) {
        mockyVoteService.get(id)
    }

    @RequestMapping(value = '/candidate/{id}/vote', method = RequestMethod.POST)
    Candidate vote(@PathVariable Long id) {
        mockyVoteService.vote(id)
    }
}
