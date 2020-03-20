package com.carloz.fintech.fintechapi.service.transaction;

import com.carloz.fintech.fintechapi.Exception.AccountNotFoundException;
import com.carloz.fintech.fintechapi.Exception.TransactionException;
import com.carloz.fintech.fintechapi.model.db.Transaction;
import com.carloz.fintech.fintechapi.repository.transaction.TransactionRepository;
import com.carloz.fintech.fintechapi.service.account.AccountService;
import com.carloz.fintech.fintechapi.service.account.IAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * TransactionService implementation encapsulates all the public business
 * behaviors for the operations on the entity {@link Transaction}
 *
 * @author Carlo Santovito
 */
@Service
public class TransactionService implements ITransactionService{

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> findByAccount(@NotNull Long accountID) {
        return transactionRepository.findByAccount(accountID);
    }

    @Override
    @Transactional
    public Optional<Transaction> createTransaction(@NotNull Long accountID, double quantity) {
        logger.info("Transaction-createTransaction: Create a transaction for the given account with credit passed");
        Transaction tran = new Transaction(accountID, quantity);
        tran = transactionRepository.save(tran);
        if(tran == null){
            throw new TransactionException("Error with transaction creation");
        }
        return Optional.of(tran);
    }


}
