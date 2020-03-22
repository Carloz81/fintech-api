package com.carloz.fintech.fintechapi.DTO;

import com.carloz.fintech.fintechapi.model.AccountInformation;
import com.carloz.fintech.fintechapi.model.db.Transaction;
import com.carloz.fintech.fintechapi.utility.ObjectMapperUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AccountInformationDTOTest {

    @Test
    @DisplayName("Conversion of a AccountInformation -> AccountInformationDTO")
    public void conversionToDTO() {
        AccountInformation ac = new AccountInformation();
        ac.setAccountNumber(1L);
        ac.setBalance(100.0);
        Transaction t = new Transaction();
        t.setId(1L);
        t.setAccountID(2L);
        t.setQuantity(100.0);
        ac.setTransactionList(Arrays.asList(t));
        AccountInformationDTO aDTO = ObjectMapperUtils.map(ac, AccountInformationDTO.class);
        Assertions.assertEquals(ac.getAccountNumber(),aDTO.getAccountNumber());
        Assertions.assertEquals(ac.getBalance(),aDTO.getBalance());
        Assertions.assertTrue(aDTO.getTransactionList().get(0) instanceof TransactionDTO);
    }

    @Test
    @DisplayName("Conversion of a AccountInformationDTO -> AccountInformation")
    public void conversionToEntity() {
        AccountInformationDTO aDTO = new AccountInformationDTO();
        aDTO.setAccountNumber(1L);
        aDTO.setBalance(100.0);
        TransactionDTO t = new TransactionDTO();
        t.setId(1L);
        t.setAccountID(2L);
        t.setQuantity(100.0);
        aDTO.setTransactionList(Arrays.asList(t));
        AccountInformation ac = ObjectMapperUtils.map(aDTO, AccountInformation.class);
        Assertions.assertEquals(ac.getAccountNumber(),aDTO.getAccountNumber());
        Assertions.assertEquals(ac.getBalance(),aDTO.getBalance());
        Assertions.assertTrue(ac.getTransactionList().get(0) instanceof Transaction);
    }
}
