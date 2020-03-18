package com.carloz.fintech.fintechapi.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BaseTransactionalEntityTest {

    @Test
    @DisplayName("Equals with equals ID")
    public void equalsOK() {
        BaseTransactionalEntity b1 = new BaseTransactionalEntity();
        b1.setId(1L);
        BaseTransactionalEntity b2 = new BaseTransactionalEntity();
        b2.setId(1L);
        Assertions.assertTrue(b1.equals(b2));
    }

    @Test
    @DisplayName("Equals with different ID")
    public void equalsDifferentID() {
        BaseTransactionalEntity b1 = new BaseTransactionalEntity();
        b1.setId(1L);
        BaseTransactionalEntity b2 = new BaseTransactionalEntity();
        b2.setId(2L);
        Assertions.assertFalse(b1.equals(b2));
    }

    @Test
    @DisplayName("Equals with NULL Object")
    public void equalsWithNull() {
        BaseTransactionalEntity b1 = new BaseTransactionalEntity();
        b1.setId(1L);
        Assertions.assertFalse(b1.equals(null));
    }

    @Test
    @DisplayName("Equals with NULL ID")
    public void equalsNullID() {
        BaseTransactionalEntity b1 = new BaseTransactionalEntity();
        b1.setId(1L);
        BaseTransactionalEntity b2 = new BaseTransactionalEntity();
        b2.setId(null);
        Assertions.assertFalse(b1.equals(b2));
        b1.setId(null);
        b2.setId(1L);
        Assertions.assertFalse(b1.equals(b2));
        b2.setId(null);
        Assertions.assertFalse(b1.equals(b2));
    }

    @Test
    @DisplayName("Equals with different Object type")
    public void equalsWithDifferentObject() {
        BaseTransactionalEntity b1 = new BaseTransactionalEntity();
        b1.setId(1L);
        Account acc = new Account();
        Assertions.assertFalse(b1.equals(acc));
    }

    @Test
    @DisplayName("Equals Object has same hashcode")
    public void equalsObjectSameHashcode() {
        BaseTransactionalEntity b1 = new BaseTransactionalEntity();
        b1.setId(1L);
        BaseTransactionalEntity b2 = new BaseTransactionalEntity();
        b2.setId(1L);
        Assertions.assertTrue(b1.hashCode() == b2.hashCode());
    }

    @Test
    @DisplayName("Not Equals Object has different hashcode")
    public void differentObjectDifferentHashcode() {
        BaseTransactionalEntity b1 = new BaseTransactionalEntity();
        b1.setId(1L);
        BaseTransactionalEntity b2 = new BaseTransactionalEntity();
        b2.setId(2L);
        Assertions.assertTrue(b1.hashCode() != b2.hashCode());
    }

}
