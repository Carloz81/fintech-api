package com.carloz.fintech.fintechapi.DTO;

import com.carloz.fintech.fintechapi.model.db.Transaction;
import com.carloz.fintech.fintechapi.utility.ObjectMapperUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TransactionDTOTest {

    @Test
    @DisplayName("Conversion of a Transaction -> TransactionDTO")
    public void conversionToDTO() {
        Transaction t = new Transaction();
        t.setId(1L);
        t.setAccountID(2L);
        t.setQuantity(100.0);
        TransactionDTO tDTO = ObjectMapperUtils.map(t,TransactionDTO.class);
        Assertions.assertTrue(t.getQuantity() == tDTO.getQuantity());
        Assertions.assertTrue(t.getAccountID().equals(tDTO.getAccountID()));
        Assertions.assertTrue(t.getId().equals(tDTO.getId()));
    }

    @Test
    @DisplayName("Conversion of a TransactionDTO -> Transaction")
    public void conversionToEntity() {
        TransactionDTO tDTO = new TransactionDTO();
        tDTO.setId(1L);
        tDTO.setAccountID(2L);
        tDTO.setQuantity(100.0);
        Transaction t = ObjectMapperUtils.map(tDTO, Transaction.class);
        Assertions.assertTrue(t.getQuantity() == tDTO.getQuantity());
        Assertions.assertTrue(t.getAccountID().equals(tDTO.getAccountID()));
        Assertions.assertTrue(t.getId().equals(tDTO.getId()));
    }

}
