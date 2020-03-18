package com.carloz.fintech.fintechapi.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Customer class describe a customer in the API and it's an entity model object.
 *
 * @author Carlo Santovito
 */
@Entity
public class Customer extends BaseTransactionalEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * No-args constructor
     */
    public Customer() {
        super();
    }

    /**
     * Create a Customer with name and surname provided.
     *
     * @param name
     * @param surname
     */
    public Customer(final String name, final String surname) {
        super();
        this.name = name;
        this.surname = surname;
    }
}
