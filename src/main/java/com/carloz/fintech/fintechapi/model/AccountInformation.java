package com.carloz.fintech.fintechapi.model;

import com.carloz.fintech.fintechapi.model.db.Account;
import com.carloz.fintech.fintechapi.model.db.Transaction;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * AccountInformationDTO class describe in detail a customer's account
 * with balance and list of its transactions
 *
 * @author Carlo Santovito
 */
public class AccountInformation {

    /**
     * Account number
     */
    private Long accountNumber;

    /**
     * Balance of the account
     */
    private double balance;

    /**
     * Account transaction list
     */
    private List<Transaction> transactionList;

    public AccountInformation(){};

    public AccountInformation(@NotNull Account account) {
        this.accountNumber = account.getId();
        this.balance = account.getBalance();
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
