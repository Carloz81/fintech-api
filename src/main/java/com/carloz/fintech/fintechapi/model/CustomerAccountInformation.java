package com.carloz.fintech.fintechapi.model;

import java.util.List;

/**
 * CustomerAccountInformation class map the informations
 * about the customer and all its accounts
 *
 * @author Carlo Santovito
 */
public class CustomerAccountInformation {

    private String customerName;

    private String customerSurname;

    private List<AccountInformation> accounts;


    public String getCustomerName() { return customerName; }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public List<AccountInformation> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountInformation> accounts) {
        this.accounts = accounts;
    }
}
