package org.mysociety.mockyvote

import groovy.transform.EqualsAndHashCode

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * Created by on 25/01/15.
 */
@Entity
@EqualsAndHashCode
class Candidate {

    @Id
    @GeneratedValue
    Long id

    String name
    Long vote
}
