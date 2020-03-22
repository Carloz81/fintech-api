package com.carloz.fintech.fintechapi.model.db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Customer class describe a customer in the API and it's an entity model object.
 *
 * @author Carlo Santovito
 */
@Entity
@Getter
@Setter
public class Customer extends BaseTransactionalEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String name;

    @NotNull
    private String surname;

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
