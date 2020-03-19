package com.carloz.fintech.fintechapi.DTO;

/**
 * TransactionDTO class is the view class for an account transaction
 *
 * @author Carlo Santovito
 */
public class TransactionDTO {

    private Long id;

    private Long accountID;

    private double quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

}
