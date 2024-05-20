package com.jave.bookStoreQuotes.values.BookStoreQuotes;


import com.jave.generic.ValueObject;

public class TotalDiscount implements ValueObject<Double> {

    private final Double totalDiscount;

    public TotalDiscount(Double total) {
        if (total == null) {
            throw new IllegalArgumentException("Total cannot be null");
        }
        if (total < 0) {
            throw new IllegalArgumentException("Total cannot be negative");
        }
        this.totalDiscount = total;
    }

    public static TotalDiscount of(Double total) {
        return new TotalDiscount(total);
    }

    @Override
    public Double value() {
        return totalDiscount;
    }
}
