package com.carloz.fintech.fintechapi.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception for entity {@link com.carloz.fintech.fintechapi.model.db.Customer} not found
 *
 * @author Carlo Santovito
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException {

    private static final String standardMessage = "Customer not found";

    public CustomerNotFoundException() {
        super(standardMessage);
    }

    public CustomerNotFoundException(final String message) {
        super(message);
    }

    public CustomerNotFoundException(final String message, final Throwable t) {
        super(message, t);
    }

    public CustomerNotFoundException(final Throwable t) {
        super(t);
    }
}
