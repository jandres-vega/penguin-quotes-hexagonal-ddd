package com.jave.bookStoreQuotes.values.copy;


import com.jave.generic.ValueObject;

public class DiscountCustomer implements ValueObject<Double> {

    private final Double discountCustomer;

    public DiscountCustomer(Double total) {
        if (total == null) {
            throw new IllegalArgumentException("Total cannot be null");
        }
        if (total < 0) {
            throw new IllegalArgumentException("Total cannot be negative");
        }
        this.discountCustomer = total;
    }

    public static DiscountCustomer of(Double total) {
        return new DiscountCustomer(total);
    }

    @Override
    public Double value() {
        return discountCustomer;
    }
}
