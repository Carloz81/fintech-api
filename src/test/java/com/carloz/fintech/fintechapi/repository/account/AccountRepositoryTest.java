package com.carloz.fintech.fintechapi.repository.account;

import com.carloz.fintech.fintechapi.model.db.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    @DisplayName("Testing the findByCustomer")
    public void findByCustomerFound() {
        accountRepository.save(new Account(1L));
        List<Account> lstAccounts = accountRepository.findByCustomer(1L);
        Assertions.assertTrue(lstAccounts.size()==1);
        Assertions.assertTrue(lstAccounts.get(0).getCustomerID().equals(1L));
        Assertions.assertTrue(lstAccounts.get(0).getBalance() == 0);
        Assertions.assertTrue(lstAccounts.get(0).getId() != null);
    }

    @Test
    @DisplayName("Testing the findByCustomer no result")
    public void findByCustomerNoResult() {
        Assertions.assertTrue(accountRepository.findByCustomer(1L).size()==0);
    }
}
