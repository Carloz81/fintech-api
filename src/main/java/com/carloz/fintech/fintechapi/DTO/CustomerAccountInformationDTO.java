package com.carloz.fintech.fintechapi.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * CustomerAccountInformationDTO class map the information to show on view
 * about the customer and all its accounts
 *
 * @author Carlo Santovito
 */
@Getter
@Setter
public class CustomerAccountInformationDTO {

    private String customerName;

    private String customerSurname;

    private List<AccountInformationDTO> accounts;
}
