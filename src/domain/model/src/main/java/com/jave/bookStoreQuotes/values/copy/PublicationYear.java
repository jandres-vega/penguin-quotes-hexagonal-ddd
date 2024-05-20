package com.jave.bookStoreQuotes.values.copy;


import com.jave.generic.ValueObject;

public class PublicationYear implements ValueObject<Integer> {

    private final Integer publicationYear;

    public PublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public Integer value() {
        return this.publicationYear;
    }
}
