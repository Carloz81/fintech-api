package com.carloz.fintech.fintechapi.model.db;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Account class describe a customer's account with customer and it's an entity model object.
 *
 * @author Carlo Santovito
 */
@Entity
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

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

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
