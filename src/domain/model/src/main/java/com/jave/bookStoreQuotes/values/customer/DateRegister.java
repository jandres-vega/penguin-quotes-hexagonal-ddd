package com.jave.bookStoreQuotes.values.customer;


import com.jave.generic.ValueObject;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateRegister implements ValueObject<String> {

    private final String date;

    public DateRegister(String date) {
        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException("Date cannot be null or empty");
        }
        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format");
        }
        this.date = date;
    }

    @Override
    public String value() {
        return date;
    }
}
