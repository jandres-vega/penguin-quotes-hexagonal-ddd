package com.jave.bookStoreQuotes.values.copy;


import com.jave.generic.ValueObject;

public class Stock implements ValueObject<Integer> {

    private final int stock;

    public Stock(int stock) {
        if( stock > 0 ){
            this.stock = stock;
        }else throw new IllegalArgumentException("stock must be greater than 0");
    }

    @Override
    public Integer value() {
        return this.stock;
    }
}
