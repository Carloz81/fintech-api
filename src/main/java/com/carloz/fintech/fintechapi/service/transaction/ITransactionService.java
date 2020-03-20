package com.carloz.fintech.fintechapi.service.transaction;

import com.carloz.fintech.fintechapi.model.db.Transaction;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * TransactionService interface expose all the public business
 * behaviors for the operations on the entity {@link Transaction}
 *
 * @author Carlo Santovito
 */
public interface ITransactionService {

    /**
     * Return all the account's {@link Transaction}
     *
     * @param accountID
     * @return a list of {@link Transaction} or an empty list
     */
    List<Transaction> findByAccount(@NotNull Long accountID);

    /**
     * Create a transaction on the given account for the given quantity.
     * If the account doesn't exist no transaction will be created.
     *
     * @param accountID
     * @param quantity
     * @return
     */
    Optional<Transaction> createTransaction(@NotNull Long accountID, double quantity);
}
