package org.mysociety.mockyvote

import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by on 25/01/15.
 */
interface ConstituencyRepository extends JpaRepository<Constituency, Long> {

    Constituency findByName(String name)
}
