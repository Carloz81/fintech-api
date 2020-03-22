package com.carloz.fintech.fintechapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * CustomerAccountInformation class map the informations
 * about the customer and all its accounts
 *
 * @author Carlo Santovito
 */
@Getter
@Setter
public class CustomerAccountInformation {

    private String customerName;

    private String customerSurname;

    private List<AccountInformation> accounts;
}
