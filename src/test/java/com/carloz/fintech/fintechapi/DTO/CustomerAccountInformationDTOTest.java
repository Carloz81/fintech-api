package com.carloz.fintech.fintechapi.DTO;

import com.carloz.fintech.fintechapi.model.AccountInformation;
import com.carloz.fintech.fintechapi.model.CustomerAccountInformation;
import com.carloz.fintech.fintechapi.model.db.Transaction;
import com.carloz.fintech.fintechapi.utility.ObjectMapperUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class CustomerAccountInformationDTOTest {

    @Test
    @DisplayName("Conversion of a CustomerAccountInformation -> CustomerAccountInformationDTO")
    public void conversionToDTO() {
        CustomerAccountInformation c = new CustomerAccountInformation();
        c.setCustomerName("name");
        c.setCustomerSurname("surname");
        AccountInformation ac = new AccountInformation();
        ac.setAccountNumber(1L);
        ac.setBalance(100.0);
        Transaction t = new Transaction();
        t.setId(1L);
        t.setAccountID(2L);
        t.setQuantity(100.0);
        ac.setTransactionList(Arrays.asList(t));
        c.setAccounts(Arrays.asList(ac));
        CustomerAccountInformationDTO cDTO = ObjectMapperUtils.map(c,CustomerAccountInformationDTO.class);
        Assertions.assertEquals(c.getCustomerName(),cDTO.getCustomerName());
        Assertions.assertEquals(c.getCustomerSurname(),cDTO.getCustomerSurname());
        Assertions.assertTrue(cDTO.getAccounts().get(0) instanceof AccountInformationDTO);
    }

    @Test
    @DisplayName("Conversion of a CustomerAccountInformationDTO -> CustomerAccountInformation")
    public void conversionToEntity() {
        CustomerAccountInformationDTO cDTO = new CustomerAccountInformationDTO();
        cDTO.setCustomerName("name");
        cDTO.setCustomerSurname("surname");
        AccountInformationDTO ac = new AccountInformationDTO();
        ac.setAccountNumber(1L);
        ac.setBalance(100.0);
        TransactionDTO t = new TransactionDTO();
        t.setId(1L);
        t.setAccountID(2L);
        t.setQuantity(100.0);
        ac.setTransactionList(Arrays.asList(t));
        cDTO.setAccounts(Arrays.asList(ac));
        CustomerAccountInformation c = ObjectMapperUtils.map(cDTO,CustomerAccountInformation.class);
        Assertions.assertEquals(c.getCustomerName(),cDTO.getCustomerName());
        Assertions.assertEquals(c.getCustomerSurname(),cDTO.getCustomerSurname());
        Assertions.assertTrue(c.getAccounts().get(0) instanceof AccountInformation);
    }
}
