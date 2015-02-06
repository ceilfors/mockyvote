package org.mysociety.mockyvote

import groovy.transform.CompileStatic
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Created by on 25/01/15.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
@CompileStatic
class ConstituencyNotFoundException extends RuntimeException {

    ConstituencyNotFoundException(String postCode) {
        super("Constituency can't be found at $postCode")
    }

    ConstituencyNotFoundException(String postCode, Throwable throwable) {
        super("Constituency can't be found at $postCode", throwable)
    }
}
