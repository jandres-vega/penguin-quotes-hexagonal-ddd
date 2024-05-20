package com.jave.bookStoreQuotes.values.BookStoreQuotes;


import com.jave.generic.ValueObject;

public class TotalPrice implements ValueObject<Double> {

    private final Double totalPrice;

    public TotalPrice(Double total) {
        if (total == null) {
            throw new IllegalArgumentException("Total cannot be null");
        }
        if (total < 0) {
            throw new IllegalArgumentException("Total cannot be negative");
        }
        this.totalPrice = total;
    }

    public static TotalPrice of(Double total) {
        return new TotalPrice(total);
    }

    @Override
    public Double value() {
        return totalPrice;
    }
}
