package com.carloz.fintech.fintechapi.repository.transaction;

import com.carloz.fintech.fintechapi.model.db.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for {@link Transaction} entities.
 *
 * @author Carlo Santovito
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Return all the account's {@link Transaction}
     *
     * @param accountID
     * @return a list of {@link Transaction} or an empty list
     */
    @Query("SELECT tran FROM Transaction tran WHERE tran.accountID = :accountID")
    List<Transaction> findByAccount(@Param("accountID") Long accountID);
}
