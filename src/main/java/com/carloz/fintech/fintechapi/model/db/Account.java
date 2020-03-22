package com.carloz.fintech.fintechapi.model.db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Account class describe a customer's account with customer and it's an entity model object.
 *
 * @author Carlo Santovito
 */
@Entity
@Getter
@Setter
public class Account extends BaseTransactionalEntity {

    private static final long serialVersionUID = 1L;

    /**
     * The ID of the owner of this account
     */
    @NotNull
    private Long customerID;

    /**
     * Balance of the account, updated every time a
     * transaction is registered on this account
     */
    private double balance;

    /**
     * No-args constructor
     */
    public Account() {
        super();
    }

    /**
     * Create an account with the given customer
     *
     * @param customerID
     */
    public Account(final Long customerID) {
        super();
        this.customerID = customerID;
    }
}
