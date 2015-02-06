package org.mysociety.mockyvote

import javax.persistence.*

/**
 * Created on 25/01/15.
 */
@Entity
class Constituency {

    @Id
    @GeneratedValue
    Long id

    @OneToMany(cascade = CascadeType.ALL)
    Set<Candidate> candidates

    String name
    String country

    // Should we have inheritance for Candidate
    Long notVoting = 0
}
