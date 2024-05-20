package com.jave.bookStoreQuotes.values.BookStoreQuotes;


import com.jave.generic.ValueObject;

import java.time.LocalDate;

public class CustomerRegistrationDate implements ValueObject<LocalDate> {

    private final LocalDate customerRegistrationDate;

    public CustomerRegistrationDate(LocalDate date) {
        if (date == null && verifyDate(date)) {
            throw new IllegalArgumentException("Total cannot be null");
        }
        this.customerRegistrationDate = date;
    }

    public static CustomerRegistrationDate of(LocalDate total) {
        return new CustomerRegistrationDate(total);
    }

    @Override
    public LocalDate value() {
        return customerRegistrationDate;
    }

    private boolean verifyDate(LocalDate registrationDate){
        return !registrationDate.isAfter(LocalDate.now());
    }

}
