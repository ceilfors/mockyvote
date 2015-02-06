package org.mysociety.mockyvote

import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by on 25/01/15.
 */
interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
