package com.carloz.fintech.fintechapi.DTO;

import java.util.List;

/**
 * CustomerAccountInformationDTO class map the information to show on view
 * about the customer and all its accounts
 *
 * @author Carlo Santovito
 */
public class CustomerAccountInformationDTO {

    private String customerName;

    private String customerSurname;

    private List<AccountInformationDTO> accounts;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public List<AccountInformationDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountInformationDTO> accounts) {
        this.accounts = accounts;
    }
}
