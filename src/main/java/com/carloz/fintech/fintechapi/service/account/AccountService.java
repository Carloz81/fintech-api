package com.carloz.fintech.fintechapi.service.account;

import com.carloz.fintech.fintechapi.Exception.AccountException;
import com.carloz.fintech.fintechapi.Exception.AccountNotFoundException;
import com.carloz.fintech.fintechapi.Exception.CustomerNotFoundException;
import com.carloz.fintech.fintechapi.Exception.TransactionException;
import com.carloz.fintech.fintechapi.model.AccountInformation;
import com.carloz.fintech.fintechapi.model.CustomerAccountInformation;
import com.carloz.fintech.fintechapi.model.db.Account;
import com.carloz.fintech.fintechapi.model.db.Customer;
import com.carloz.fintech.fintechapi.model.db.Transaction;
import com.carloz.fintech.fintechapi.repository.account.AccountRepository;
import com.carloz.fintech.fintechapi.service.cutomer.CustomerService;
import com.carloz.fintech.fintechapi.service.cutomer.ICustomerService;
import com.carloz.fintech.fintechapi.service.transaction.ITransactionService;
import com.carloz.fintech.fintechapi.service.transaction.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * AccountService implementation encapsulates all the public business
 * behaviors for the operations on the entity {@link Account}
 *
 * @author Carlo Santovito
 */

@Service
public class AccountService implements IAccountService{

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private final AccountRepository accountRepository;

    private final ITransactionService iTransactionService;

    private final ICustomerService iCustomerService;

    public AccountService(ITransactionService iTransactionService,
                          ICustomerService iCustomerService,
                          AccountRepository accountRepository) {

        this.iTransactionService = iTransactionService;
        this.iCustomerService = iCustomerService;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> findByCustomer(@NotNull Long customerID) {
        return accountRepository.findByCustomer(customerID);
    }

    @Override
    public boolean checkAccountExists(@NotNull Long accountID) {
        return accountRepository.existsById(accountID);
    }

    @Override
    @Transactional
    public Account saveAccount(@NotNull Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<AccountInformation> findInformationByID(@NotNull Long accountID) {
        logger.info("Account-findInformationByID: check if the account exists");
        Optional<Account> accountOptional = accountRepository.findById(accountID);
        if(!accountOptional.isPresent()){
            throw new AccountNotFoundException();
        }

        logger.info("Account-findInformationByID: Create AccountInformation with the account informations");
        AccountInformation accountInformation = new AccountInformation();
        accountInformation.setBalance(accountOptional.get().getBalance());
        accountInformation.setAccountNumber(accountOptional.get().getId());

        logger.info("Account-findInformationByID: load all the transactions of the account");
        accountInformation.setTransactionList(iTransactionService.findByAccount(accountID));
        return Optional.of(accountInformation);
    }

    @Override
    @Transactional
    public Optional<Account> createNewAccountByCustomer(@NotNull Long customerID, double initialCredit) {
        logger.info("Account-createNewAccountByCustomer: check if customer exists");
        if(!iCustomerService.checkCustomerExists(customerID)){
            throw new CustomerNotFoundException();
        }

        logger.info("Account-createNewAccountByCustomer: create and save new account for the given customer");
        Account newAccount = new Account(customerID);
        newAccount = accountRepository.save(newAccount);
        if(newAccount==null) {
            throw new AccountException("Error while saving the new account");
        }
        logger.info("Account-createNewAccountByCustomer: check if initialCredit is not 0, if true create the first transaction on the account");
        if(initialCredit != 0){
            Optional<Transaction> firstTransaction = iTransactionService.createTransaction(newAccount.getId(), initialCredit);
            logger.info("Account-createNewAccountByCustomer: first transaction created, update balance on account");
            newAccount.setBalance(firstTransaction.get().getQuantity());
            newAccount = accountRepository.save(newAccount);
        }
        return Optional.of(newAccount);
    }

    @Override
    public Optional<CustomerAccountInformation> getCustomerAccountInfo(@NotNull Long customerID) {
        logger.info("Account-getCustomerAccountInfo: get customer informations");
        Optional<Customer> customerOptional = iCustomerService.findByID(customerID);
        if(!customerOptional.isPresent()) {
            throw new CustomerNotFoundException();
        }
        CustomerAccountInformation customerAccountInformation = new CustomerAccountInformation();
        customerAccountInformation.setCustomerName(customerOptional.get().getName());
        customerAccountInformation.setCustomerSurname(customerOptional.get().getSurname());

        logger.info("Account-getCustomerAccountInfo: get all the customer's account");
        List<Account> lstAccount = findByCustomer(customerID);

        if(!lstAccount.isEmpty()) {
            logger.info("Account-getCustomerAccountInfo: found account's customer, retrieve account information");
            List<AccountInformation> lstAccInformation = lstAccount.stream()
                      .map(account -> new AccountInformation(account))
                      .collect(Collectors.toList());
            lstAccInformation.stream().forEach(accInfo -> accInfo.setTransactionList(iTransactionService.findByAccount(accInfo.getAccountNumber())));
            customerAccountInformation.setAccounts(lstAccInformation);
        }else{
            logger.info("Account-getCustomerAccountInfo: no accounts found for the customer");
            customerAccountInformation.setAccounts(Collections.emptyList());
        }

        return Optional.of(customerAccountInformation);
    }
}
