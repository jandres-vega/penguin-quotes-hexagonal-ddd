package com.jave.bookStoreQuotes.values.copy;

import com.jave.generic.ValueObject;

public class Author implements ValueObject<String> {

    private final String author;

    public Author(String author) {
        this.author = author;
    }

    @Override
    public String value() {
        return author;
    }
}
