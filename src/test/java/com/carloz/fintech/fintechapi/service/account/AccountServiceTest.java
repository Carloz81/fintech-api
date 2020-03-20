package com.carloz.fintech.fintechapi.service.account;

import com.carloz.fintech.fintechapi.Exception.AccountNotFoundException;
import com.carloz.fintech.fintechapi.Exception.CustomerNotFoundException;
import com.carloz.fintech.fintechapi.Exception.TransactionException;
import com.carloz.fintech.fintechapi.model.AccountInformation;
import com.carloz.fintech.fintechapi.model.CustomerAccountInformation;
import com.carloz.fintech.fintechapi.model.db.Account;
import com.carloz.fintech.fintechapi.model.db.Customer;
import com.carloz.fintech.fintechapi.model.db.Transaction;
import com.carloz.fintech.fintechapi.repository.account.AccountRepository;
import com.carloz.fintech.fintechapi.service.cutomer.ICustomerService;
import com.carloz.fintech.fintechapi.service.transaction.ITransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


public class AccountServiceTest {

    AccountService accountService;

    AccountRepository accountRepository = Mockito.mock(AccountRepository.class);

    ITransactionService iTransactionService = Mockito.mock(ITransactionService.class);

    ICustomerService iCustomerService = Mockito.mock(ICustomerService.class);

    @BeforeEach
    void setup() {
        accountService = new AccountService(iTransactionService, iCustomerService, accountRepository);
    }

    @Test
    @DisplayName("findByCustomer with result")
    void findByCustomerOK() {
        Mockito.when(accountRepository.findByCustomer(1L)).thenReturn(Arrays.asList(new Account(1L)));
        List<Account> lstAccount = accountService.findByCustomer(1L);
        Assertions.assertTrue(lstAccount.size()==1);
        Assertions.assertTrue(lstAccount.get(0).getCustomerID().equals(1L));
    }

    @Test
    @DisplayName("findByCustomer with NO result")
    void findByCustomerNoResult() {
        Mockito.when(accountRepository.findByCustomer(1L)).thenReturn(Collections.emptyList());
        List<Account> lstAccount = accountService.findByCustomer(1L);
        Assertions.assertTrue(lstAccount.isEmpty());
    }

    @Test
    @DisplayName("findInformationByID OK test")
    void findInformationByIDOk() {
        Account acc = new Account(99L);
        acc.setId(1L);
        acc.setBalance(100);
        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(acc));
        Mockito.when(iTransactionService.findByAccount(1L))
                                        .thenReturn(Arrays.asList(new Transaction(1L,100)));
        Optional<AccountInformation> optionalAccountInformation = accountService.findInformationByID(1L);
        Assertions.assertTrue(optionalAccountInformation.isPresent());
        Assertions.assertTrue(optionalAccountInformation.get().getBalance() == 100);
        Assertions.assertTrue(optionalAccountInformation.get().getAccountNumber().equals(1L));
        Assertions.assertTrue(optionalAccountInformation.get().getTransactionList().size() == 1);
        Assertions.assertTrue(optionalAccountInformation.get().getTransactionList().get(0).getAccountID().equals(1L));
    }

    @Test
    @DisplayName("findInformationByID No account found")
    void findInformationByIDNoAccount() {
        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(AccountNotFoundException.class, () -> {
            accountService.findInformationByID(1L);
        });
    }

    @Test
    @DisplayName("findInformationByID No account's transactions")
    void findInformationByIDNoTransactions() {
        Account acc = new Account(99L);
        acc.setId(1L);
        acc.setBalance(100);
        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(acc));
        Mockito.when(iTransactionService.findByAccount(1L)).thenReturn(Collections.emptyList());
        Optional<AccountInformation> optionalAccountInformation = accountService.findInformationByID(1L);
        Assertions.assertTrue(optionalAccountInformation.isPresent());
        Assertions.assertTrue(optionalAccountInformation.get().getBalance() == 100);
        Assertions.assertTrue(optionalAccountInformation.get().getAccountNumber().equals(1L));
        Assertions.assertTrue(optionalAccountInformation.get().getTransactionList().isEmpty());
    }

    @Test
    @DisplayName("findInformationByID No account ID provided")
    void findInformationByIDNoParams() {
        Assertions.assertThrows(AccountNotFoundException.class, () -> {
            accountService.findInformationByID(null);
        });
    }

    @Test
    @DisplayName("createNewAccountByCustomer Ok result")
    void createNewAccountByCustomerOK() {
        Mockito.when(iCustomerService.checkCustomerExists(2L)).thenReturn(true);
        Account acc = new Account(99L);
        acc.setId(1L);
        acc.setBalance(100);
        acc.setCustomerID(2L);
        Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(acc);
        Mockito.when(iTransactionService.createTransaction(1L,100))
                                        .thenReturn(Optional.of(new Transaction(1L,100)));
        Optional<Account> optionalAccount = accountService.createNewAccountByCustomer(2L,100);
        Assertions.assertTrue(optionalAccount.isPresent());
        Assertions.assertTrue(optionalAccount.get().getId().equals(1L));
        Assertions.assertTrue(optionalAccount.get().getCustomerID().equals(2L));
        Assertions.assertTrue(optionalAccount.get().getBalance() == acc.getBalance());
    }

    @Test
    @DisplayName("createNewAccountByCustomer null CustomerID or no Customer found")
    void createNewAccountByCustomer2() {
        Mockito.when(iCustomerService.checkCustomerExists(2L)).thenReturn(false);
        Assertions.assertThrows(CustomerNotFoundException.class, () -> {
            accountService.createNewAccountByCustomer(2L,0);
        });

        Mockito.when(iCustomerService.checkCustomerExists(null)).thenReturn(false);
        Assertions.assertThrows(CustomerNotFoundException.class, () -> {
            accountService.createNewAccountByCustomer(null,0);
        });
    }

    @Test
    @DisplayName("createNewAccountByCustomer creation of transaction exception")
    void createNewAccountByCustomer3() {
        Mockito.when(iCustomerService.checkCustomerExists(2L)).thenReturn(true);
        Account acc = new Account(99L);
        acc.setId(1L);
        acc.setBalance(100);
        acc.setCustomerID(2L);
        Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(acc);
        Mockito.when(iTransactionService.createTransaction(1L,100)).thenThrow(new TransactionException("Error with transaction creation"));

        TransactionException exception = Assertions.assertThrows(TransactionException.class,
                () -> accountService.createNewAccountByCustomer(2L,100));

        String expectedMessage = "Error with transaction creation";
        Assertions.assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    @DisplayName("getCustomerAccountInfo test OK")
    void getCustomerAccountInfoOK() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Ridley");
        customer.setSurname("Scott");
        customer.setCreationTime(Instant.now());
        Mockito.when(iCustomerService.findByID(1L)).thenReturn(Optional.of(customer));

        Account account = new Account(customer.getId());
        account.setId(2L);
        account.setBalance(100);
        account.setCreationTime(Instant.now());
        Mockito.when(accountRepository.findByCustomer(customer.getId())).thenReturn(Arrays.asList(account));

        Transaction transaction = new Transaction();
        transaction.setId(3L);
        transaction.setAccountID(account.getId());
        transaction.setQuantity(100);
        transaction.setCreationTime(Instant.now());
        Mockito.when(iTransactionService.findByAccount(account.getId())).thenReturn(Arrays.asList(transaction));

        Optional<CustomerAccountInformation> optionalCustomerAccountInformation = accountService.getCustomerAccountInfo(1L);

        Assertions.assertTrue(optionalCustomerAccountInformation.isPresent());
        Assertions.assertTrue(optionalCustomerAccountInformation.get().getCustomerName().equals(customer.getName()));
        Assertions.assertTrue(optionalCustomerAccountInformation.get().getCustomerSurname().equals(customer.getSurname()));
        Assertions.assertTrue(optionalCustomerAccountInformation.get().getAccounts().size() == 1);
        Assertions.assertTrue(optionalCustomerAccountInformation.get().getAccounts().get(0).getAccountNumber().equals(account.getId()));
        Assertions.assertTrue(optionalCustomerAccountInformation.get().getAccounts().get(0).getBalance() == account.getBalance());
        Assertions.assertTrue(optionalCustomerAccountInformation.get().getAccounts().get(0).getTransactionList().size() == 1);
        Assertions.assertTrue(optionalCustomerAccountInformation.get().getAccounts().get(0).getTransactionList().get(0).getAccountID().equals(account.getId()));
        Assertions.assertTrue(optionalCustomerAccountInformation.get().getAccounts().get(0).getTransactionList().get(0).getQuantity() == transaction.getQuantity());
    }

    @Test
    @DisplayName("getCustomerAccountInfo No customer found")
    void getCustomerAccountInfoKO1() {
        Mockito.when(iCustomerService.findByID(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(CustomerNotFoundException.class, () -> {
            accountService.getCustomerAccountInfo(1L);
        });
    }

    @Test
    @DisplayName("getCustomerAccountInfo No accounts found")
    void getCustomerAccountInfoNoAccount() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Ridley");
        customer.setSurname("Scott");
        customer.setCreationTime(Instant.now());
        Mockito.when(iCustomerService.findByID(1L)).thenReturn(Optional.of(customer));

        Mockito.when(accountRepository.findByCustomer(customer.getId())).thenReturn(Collections.emptyList());

        Optional<CustomerAccountInformation> optionalCustomerAccountInformation = accountService.getCustomerAccountInfo(1L);

        Assertions.assertTrue(optionalCustomerAccountInformation.isPresent());
        Assertions.assertTrue(optionalCustomerAccountInformation.get().getCustomerName().equals(customer.getName()));
        Assertions.assertTrue(optionalCustomerAccountInformation.get().getCustomerSurname().equals(customer.getSurname()));
        Assertions.assertTrue(optionalCustomerAccountInformation.get().getAccounts().size() == 0);
    }

    @Test
    @DisplayName("getCustomerAccountInfo No transactions found")
    void getCustomerAccountInfoNoTransaction() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Ridley");
        customer.setSurname("Scott");
        customer.setCreationTime(Instant.now());
        Mockito.when(iCustomerService.findByID(1L)).thenReturn(Optional.of(customer));

        Account account = new Account(customer.getId());
        account.setId(2L);
        account.setBalance(100);
        account.setCreationTime(Instant.now());
        Mockito.when(accountRepository.findByCustomer(customer.getId())).thenReturn(Arrays.asList(account));

        Mockito.when(iTransactionService.findByAccount(account.getId())).thenReturn(Collections.emptyList());

        Optional<CustomerAccountInformation> optionalCustomerAccountInformation = accountService.getCustomerAccountInfo(1L);

        Assertions.assertTrue(optionalCustomerAccountInformation.isPresent());
        Assertions.assertTrue(optionalCustomerAccountInformation.get().getCustomerName().equals(customer.getName()));
        Assertions.assertTrue(optionalCustomerAccountInformation.get().getCustomerSurname().equals(customer.getSurname()));
        Assertions.assertTrue(optionalCustomerAccountInformation.get().getAccounts().size() == 1);
        Assertions.assertTrue(optionalCustomerAccountInformation.get().getAccounts().get(0).getAccountNumber().equals(account.getId()));
        Assertions.assertTrue(optionalCustomerAccountInformation.get().getAccounts().get(0).getBalance() == account.getBalance());
        Assertions.assertTrue(optionalCustomerAccountInformation.get().getAccounts().get(0).getTransactionList().size() == 0);
    }
}
