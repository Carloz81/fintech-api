package com.carloz.fintech.fintechapi.service.transaction;

import com.carloz.fintech.fintechapi.Exception.TransactionException;
import com.carloz.fintech.fintechapi.model.db.Transaction;
import com.carloz.fintech.fintechapi.repository.transaction.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

class TransactionServiceTest {

    private final TransactionRepository transactionRepository = Mockito.mock(TransactionRepository.class);

    TransactionService transactionService;

    @BeforeEach
    void setup() {
        transactionService = new TransactionService(transactionRepository);
    }

    @Test
    @DisplayName("createTransaction test OK")
    void createTransactionOK() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setQuantity(100);
        transaction.setAccountID(2L);
        Mockito.when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(transaction);

        Optional<Transaction> optionalTransaction = transactionService.createTransaction(2L,100);

        Assertions.assertTrue(optionalTransaction.isPresent());
        Assertions.assertTrue(optionalTransaction.get().getQuantity() == 100);
        Assertions.assertTrue(optionalTransaction.get().getAccountID().equals(2L));
        Assertions.assertTrue(optionalTransaction.get().getId().equals(1L));
    }

    @Test
    @DisplayName("createTransaction save KO with exception")
    void createTransactionKO() {
        Mockito.when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(null);

        TransactionException exception = Assertions.assertThrows(TransactionException.class,
                () -> transactionService.createTransaction(2L,100));

        String expectedMessage = "Error with transaction creation";
        Assertions.assertTrue(exception.getMessage().contains(expectedMessage));
    }

}
