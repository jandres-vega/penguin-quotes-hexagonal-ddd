package com.jave.bookStoreQuotes.values.quote;

import com.jave.generic.ValueObject;

public class Total implements ValueObject<Double> {

    private final Double total;

    public Total(Double total) {
        if (total == null) {
            throw new IllegalArgumentException("Total cannot be null");
        }
        if (total < 0) {
            throw new IllegalArgumentException("Total cannot be negative");
        }
        this.total = total;
    }

    @Override
    public Double value() {
        return total;
    }
}
