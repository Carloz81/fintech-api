package com.carloz.fintech.fintechapi.repository.customer;

import com.carloz.fintech.fintechapi.model.db.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for {@link Customer} entities.
 *
 * @author Carlo Santovito
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
