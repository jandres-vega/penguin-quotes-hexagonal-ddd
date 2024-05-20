package com.jave.bookStoreQuotes.values.customer;


import com.jave.generic.ValueObject;

public class Name implements ValueObject<String> {

    private final String name;

    public Name(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    @Override
    public String value() {
        return name;
    }
}
