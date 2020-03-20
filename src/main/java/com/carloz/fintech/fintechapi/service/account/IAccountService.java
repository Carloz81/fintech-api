package com.carloz.fintech.fintechapi.service.account;

import com.carloz.fintech.fintechapi.model.AccountInformation;
import com.carloz.fintech.fintechapi.model.CustomerAccountInformation;
import com.carloz.fintech.fintechapi.model.db.Account;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * AccountService interface expose all the public business
 * behaviors for the operations on the entity {@link Account}
 *
 * @author Carlo Santovito
 */
public interface IAccountService {

    /**
     * Return all the customer's {@link Account}
     *
     * @param customerID
     * @return a list of {@link Account}
     */
    List<Account> findByCustomer(@NotNull Long customerID);

    /**
     * Check if an account with the given ID exists.
     *
     * @param accountID
     * @return
     */
    boolean checkAccountExists(@NotNull Long accountID);

    /**
     * Save an account and retunrn it
     *
     * @param account
     * @return Saved {@link Account}
     */
    Account saveAccount(@NotNull Account account);

    /**
     * Return the {@link AccountInformation} for the given account ID.
     *
     * @param accountID
     * @return an Optional of {@link AccountInformation}
     */
    Optional<AccountInformation> findInformationByID(@NotNull Long accountID);

    /**
     * Create a new account for the given customer. If <code>initialCredit</code>
     * is provided with a value > 0 a transaction will be sent to the new account
     * and account balance will be updated too.
     *
     * @param customerID
     * @param initialCredit
     * @return an Optional of {@link Account}
     */
    Optional<Account> createNewAccountByCustomer(@NotNull Long customerID, double initialCredit);

    /**
     * Return a {@link CustomerAccountInformation} class that wrap all the information
     * about the customer and customer's accounts for the given customer ID.
     *
     * @param customerID
     * @return an Optional of {@link CustomerAccountInformation}
     */
    Optional<CustomerAccountInformation> getCustomerAccountInfo(@NotNull Long customerID);
}
