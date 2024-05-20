package com.jave.bookStoreQuotes.values.copy;


import com.jave.generic.ValueObject;

public class Price implements ValueObject<Double> {

    private final double price;

    public Price(double price) {
        if(price > 0){
            this.price = price;
        }else throw new IllegalArgumentException("price must be greater than 0");
    }

    public static Price of(double price){
        return new Price(price);
    }

    @Override
    public Double value() {
        return this.price;
    }
}
