package com.carloz.fintech.fintechapi.service.customer;

import com.carloz.fintech.fintechapi.model.db.Customer;
import com.carloz.fintech.fintechapi.repository.customer.CustomerRepository;
import com.carloz.fintech.fintechapi.service.cutomer.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

class CustomerServiceTest {

    private CustomerService customerService;

    private CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);

    @BeforeEach
    void setup() {
        customerService = new CustomerService(customerRepository);
    }


    @Test
    @DisplayName("Find by id with results")
    void findByIDWithResult() {
        Customer c = new Customer();
        c.setId(1L);
        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(c));
        Optional<Customer> customerOptional = customerService.findByID(1L);
        Assertions.assertTrue(customerOptional.get().getId().equals(1L));
    }

    @Test
    @DisplayName("Find by id with NO results")
    void findByIDWithNOResult() {
        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<Customer> customerOptional = customerService.findByID(1L);
        Assertions.assertFalse(customerOptional.isPresent());
    }

    @Test
    @DisplayName("checkCustomerExists with true result")
    void checkCustomerExistsOK() {
        Mockito.when(customerRepository.existsById(1L)).thenReturn(true);
        Assertions.assertTrue(customerService.checkCustomerExists(1L));
    }

    @Test
    @DisplayName("checkCustomerExists with false result")
    void checkCustomerExistsKO() {
        Mockito.when(customerRepository.existsById(1L)).thenReturn(false);
        Assertions.assertFalse(customerService.checkCustomerExists(1L));
    }
}
