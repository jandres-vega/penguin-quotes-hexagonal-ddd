package com.jave.bookStoreQuotes.values.copy;


import com.jave.generic.ValueObject;

public class DiscountMayor implements ValueObject<Double> {

    private final Double discountMayor;

    public DiscountMayor(Double total) {
        if (total == null) {
            throw new IllegalArgumentException("Total cannot be null");
        }
        if (total < 0) {
            throw new IllegalArgumentException("Total cannot be negative");
        }
        this.discountMayor = total;
    }

    public static DiscountMayor of(Double total) {
        return new DiscountMayor(total);
    }

    @Override
    public Double value() {
        return discountMayor;
    }
}
