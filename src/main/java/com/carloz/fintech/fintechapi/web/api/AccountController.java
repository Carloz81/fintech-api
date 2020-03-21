package com.carloz.fintech.fintechapi.web.api;

import com.carloz.fintech.fintechapi.DTO.AccountInformationDTO;
import com.carloz.fintech.fintechapi.DTO.CustomerAccountInformationDTO;
import com.carloz.fintech.fintechapi.model.AccountInformation;
import com.carloz.fintech.fintechapi.model.CustomerAccountInformation;
import com.carloz.fintech.fintechapi.model.db.Account;
import com.carloz.fintech.fintechapi.service.account.IAccountService;
import com.carloz.fintech.fintechapi.utility.ObjectMapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * The AccountController class is a RESTful web service controller
 * that manage all the requests that regards an Account
 *
 * @author Carlo Santovito
 */
@RestController
@RequestMapping("api/v1/account")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final IAccountService iAccountService;

    public AccountController(IAccountService iAccountService) {
        this.iAccountService = iAccountService;
    }

    /**
     * Web service endpoint to create a new account for the given customer.
     * If initial credit is not 0 an transaction will be sent to the new account.
     * Account balance will be updated to initialCredit.
     *
     * @param customerID
     * @param initialCredit
     * @return AccountInformationDTO and an HttpStatus code CREATED if successful
     */
    @PostMapping("/create-account")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountInformationDTO createAccount(@RequestParam(value = "customerID") final Long customerID,
                                               @RequestParam(value = "initialCredit") final double initialCredit) {
        logger.info("AccountController-createAccount: create new account for the given customer id {}",customerID);
        Optional<Account> account = iAccountService.createNewAccountByCustomer(customerID, initialCredit);
        logger.info("AccountController-createAccount: retrieve the account information class for the account created {}",account.get().getId());
        Optional<AccountInformation> accountInformationOpt = iAccountService.findInformationByID(account.get().getId());
        return ObjectMapperUtils.map(accountInformationOpt.get(), AccountInformationDTO.class);
    }

    /**
     * Web service endpoint to retrieve the full information about a customer's accounts
     * and all transactions related.
     *
     * @param customerId
     * @return CustomerAccountInformationDTO
     */
    @GetMapping("/get-customer-account-info/{customerId}")
    public CustomerAccountInformationDTO getCustomerAccountInfo(@PathVariable final Long customerId) {
        logger.info("AccountController-getCustomerAccountInfo: retrieve the customer's account informations for customer id {}",customerId);
        Optional<CustomerAccountInformation> customerAccountInformationOptional = iAccountService.getCustomerAccountInfo(customerId);
        return ObjectMapperUtils.map(customerAccountInformationOptional.get(), CustomerAccountInformationDTO.class);
    }

}
