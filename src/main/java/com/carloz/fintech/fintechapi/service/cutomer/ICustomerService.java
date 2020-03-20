package com.carloz.fintech.fintechapi.service.cutomer;

import com.carloz.fintech.fintechapi.model.db.Customer;

import java.util.Optional;

/**
 * CustomerService interface expose all the public business
 * behaviors for the operations on the entity {@link Customer}
 *
 * @author Carlo Santovito
 */
public interface ICustomerService {

    /**
     * Return {@link Customer} for the given customer ID
     *
     * @param customerID
     * @return Optional of {@link Customer}
     */
    public Optional<Customer> findByID(Long customerID);

    /**
     * Check if a customer with the given ID exists.
     *
     * @param customerID
     * @return
     */
    public boolean checkCustomerExists(Long customerID);
}
