package com.carloz.fintech.fintechapi.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * AccountInformationDTO class describe in detail a customer's account
 * with balance and list of its transactions
 *
 * @author Carlo Santovito
 */
@Getter
@Setter
public class AccountInformationDTO {

    private Long accountNumber;

    private double balance;

    private List<TransactionDTO> transactionList;

}
