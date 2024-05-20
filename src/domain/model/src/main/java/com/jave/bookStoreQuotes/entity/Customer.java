package com.jave.bookStoreQuotes.entity;


import com.jave.bookStoreQuotes.values.customer.DateRegister;
import com.jave.bookStoreQuotes.values.customer.Name;
import com.jave.bookStoreQuotes.values.identities.CustomerId;
import com.jave.generic.Entity;

public class Customer extends Entity<CustomerId> {

    private final Name name;
    private final DateRegister date;

    public Customer(Name name,  DateRegister date) {
        super(new CustomerId());
        this.name = name;
        this.date = date;
    }

    public static Customer from(
            Name name,
            DateRegister date
    ){
        return new Customer(name, date);
    }

    public void calculateDiscount(){}

    public void calculateSeniority(){}
}
