package com.carloz.fintech.fintechapi.repository.account;

import com.carloz.fintech.fintechapi.model.db.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Return all the customer's {@link Account}
     *
     * @param customerID
     * @return a list of {@link Account} or an empty list
     */
    @Query("SELECT acc FROM Account acc WHERE acc.customerID = :customerID")
    List<Account> findByCustomer(@Param("customerID") Long customerID);

}
