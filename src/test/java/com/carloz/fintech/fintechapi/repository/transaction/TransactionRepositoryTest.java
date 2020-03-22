package com.carloz.fintech.fintechapi.repository.transaction;

import com.carloz.fintech.fintechapi.model.db.Account;
import com.carloz.fintech.fintechapi.model.db.Transaction;
import com.carloz.fintech.fintechapi.repository.account.AccountRepository;
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

    @Autowired
    AccountRepository accountRepository;

    @Test
    @DisplayName("Testing the findByAccount with results")
    public void findByAccountFound() {
        Account account1 = accountRepository.save(new Account(1L));
        Account account2 = accountRepository.save(new Account(2L));

        transactionRepository.save(new Transaction(account1.getId(),0));
        List<Transaction> lstFoundTransaction1 = transactionRepository.findByAccount(account1.getId());
        Assertions.assertTrue(lstFoundTransaction1.size()==1);
        Assertions.assertTrue(lstFoundTransaction1.get(0).getAccountID().equals(account1.getId()));

        transactionRepository.save(new Transaction(account2.getId(),10));
        List<Transaction> lstFoundTransaction2 = transactionRepository.findByAccount(account2.getId());
        Assertions.assertTrue(lstFoundTransaction2.size()==1);
        Assertions.assertTrue(lstFoundTransaction2.get(0).getAccountID().equals(account2.getId()));
        Assertions.assertTrue(lstFoundTransaction2.get(0).getQuantity() == 10);
    }

    @Test
    @DisplayName("Testing the findByAccount no results")
    public void findByAccountNoResult() {
        Assertions.assertTrue(transactionRepository.findByAccount(1L).size()==0);
    }
}
