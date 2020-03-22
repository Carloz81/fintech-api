package com.carloz.fintech.fintechapi.DTO;

import lombok.Getter;
import lombok.Setter;

/**
 * TransactionDTO class is the view class for an account transaction
 *
 * @author Carlo Santovito
 */
@Getter
@Setter
public class TransactionDTO {

    private Long id;

    private Long accountID;

    private double quantity;
}
