package com.jave.bookStoreQuotes.values.copy;


import com.jave.generic.ValueObject;

public class Title implements ValueObject<String> {

    private final String title;

    public Title(String title) {
        if(!title.isEmpty() && title.length() < 20){
            this.title = title;
        }else throw new IllegalArgumentException("title must be between 1 and 10");
    }

    @Override
    public String value() {
        return this.title;
    }
}
