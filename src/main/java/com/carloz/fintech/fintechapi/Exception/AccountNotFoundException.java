package com.carloz.fintech.fintechapi.Exception;

/**
 * Custom exception for entity {@link com.carloz.fintech.fintechapi.model.db.Account} not found
 *
 * @author Carlo Santovito
 */
public class AccountNotFoundException extends RuntimeException {

    private static final String standardMessage = "Account not found";

    public AccountNotFoundException() { super(standardMessage);}

    public AccountNotFoundException(final String message) {
        super(message);
    }

    public AccountNotFoundException(final String message, final Throwable t) {
        super(message, t);
    }

    public AccountNotFoundException(final Throwable t) {
        super(t);
    }
}
