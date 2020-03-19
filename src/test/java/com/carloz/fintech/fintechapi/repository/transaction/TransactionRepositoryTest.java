package com.carloz.fintech.fintechapi.repository.transaction;

import com.carloz.fintech.fintechapi.model.db.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class TransactionRepositoryTest {

    @Autowired
    TransactionRepository transactionRepository;

    @Test
    @DisplayName("Testing the findByAccount")
    public void findByAccountFound() {
        transactionRepository.save(new Transaction(1L,0));
        List<Transaction> lstFoundTransaction1 = transactionRepository.findByAccount(1L);
        Assertions.assertTrue(lstFoundTransaction1.size()==1);
        Assertions.assertTrue(lstFoundTransaction1.get(0).getAccountID().equals(1L));

        transactionRepository.save(new Transaction(2L,10));
        List<Transaction> lstFoundTransaction2 = transactionRepository.findByAccount(2L);
        Assertions.assertTrue(lstFoundTransaction2.size()==1);
        Assertions.assertTrue(lstFoundTransaction2.get(0).getAccountID().equals(2L));
        Assertions.assertTrue(lstFoundTransaction2.get(0).getQuantity() == 10);
    }

    @Test
    @DisplayName("Testing the findByAccount no result")
    public void findByAccountNoResult() {
        Assertions.assertTrue(transactionRepository.findByAccount(1L).size()==0);
    }
}
