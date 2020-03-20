package com.carloz.fintech.fintechapi.Exception;

/**
 * Custom exception for entity {@link com.carloz.fintech.fintechapi.model.db.Transaction}
 *
 * @author Carlo Santovito
 */
public class TransactionException extends RuntimeException {

    public TransactionException() {
        super();
    }

    public TransactionException(final String message) {
        super(message);
    }

    public TransactionException(final String message, final Throwable t) {
        super(message, t);
    }

    public TransactionException(final Throwable t) {
        super(t);
    }
}
