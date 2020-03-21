package com.carloz.fintech.fintechapi.Exception;

/**
 * Custom exception for entity {@link com.carloz.fintech.fintechapi.model.db.Account}
 *
 * @author Carlo Santovito
 */
public class AccountException extends RuntimeException {

    public AccountException() {
        super();
    }

    public AccountException(final String message) {
        super(message);
    }

    public AccountException(final String message, final Throwable t) {
        super(message, t);
    }

    public AccountException(final Throwable t) {
        super(t);
    }
}
