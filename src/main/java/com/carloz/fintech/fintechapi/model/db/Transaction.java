package com.carloz.fintech.fintechapi.model.db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Transaction class describe a single transaction made on an account
 * and it's an entity model object.
 *
 * @author Carlo Santovito
 */
@Entity
@Getter
@Setter
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
