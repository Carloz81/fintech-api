package com.carloz.fintech.fintechapi.DTO;

import java.util.List;

/**
 * AccountInformationDTO class describe in detail a customer's account
 * with balance and list of its transactions
 *
 * @author Carlo Santovito
 */
public class AccountInformationDTO {

    private Long accountNumber;

    private double balance;

    private List<TransactionDTO> transactionList;


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

    public List<TransactionDTO> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<TransactionDTO> transactionList) {
        this.transactionList = transactionList;
    }
}
