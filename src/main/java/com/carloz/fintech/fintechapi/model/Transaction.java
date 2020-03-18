package com.carloz.fintech.fintechapi.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Transaction class describe a single transaction made on an account
 * and it's an entity model object.
 *
 * @author Carlo Santovito
 */
@Entity
public class Transaction extends BaseTransactionalEntity {

    private static final long serialVersionUID = 1L;

    /**
     * Account associated to this transaction
     */
    @NotNull
    private Long accountID;

    /**
     * Quantity associated to this transaction,
     * can be positive-negative-zero
     */
    @NotNull
    private double quantity;

    public Long getAccountID() {
        return accountID;
    }

    public void setAccountID(Long accountID) {
        this.accountID = accountID;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * No-args constructor
     */
    public Transaction() {
        super();
    }

    /**
     * Create a transaction for the provided account and set the
     * quantity to transactionQuantity
     *
     * @param accountID
     * @param transactionQuantity
     */
    public Transaction(final Long accountID, final double transactionQuantity) {
        this.accountID = accountID;
        this.quantity = transactionQuantity;
    }
}
