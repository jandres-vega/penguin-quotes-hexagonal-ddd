package com.jave.bookStoreQuotes.values.copy;


import com.jave.generic.ValueObject;

public class Type implements ValueObject<String> {

    private final String type;

    public Type(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }
        this.type = type;
    }


    @Override
    public String value() {
        return this.type;
    }
}

