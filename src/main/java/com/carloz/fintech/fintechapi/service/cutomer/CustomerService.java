package com.carloz.fintech.fintechapi.service.cutomer;

import com.carloz.fintech.fintechapi.model.db.Customer;
import com.carloz.fintech.fintechapi.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * CustomerService implementation encapsulates all the public business
 * behaviors for the operations on the entity {@link Customer}
 *
 * @author Carlo Santovito
 */
@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Customer> findByID(Long customerID) {
        return customerRepository.findById(customerID);
    }

    @Override
    public boolean checkCustomerExists(Long customerID) {
        return customerRepository.existsById(customerID);
    }
}
