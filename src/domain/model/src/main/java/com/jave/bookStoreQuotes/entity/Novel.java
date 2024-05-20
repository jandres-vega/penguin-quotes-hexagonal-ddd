package com.jave.bookStoreQuotes.entity;


import com.jave.bookStoreQuotes.values.copy.*;

import java.util.Comparator;
import java.util.List;


public class Novel extends Copy{


    public Novel(String id, Type type, Title title, Author author, Stock stock, Price price, PublicationYear publicationYear) {
        super(id, type, title, author, stock, price, publicationYear);
        calculateIndividualPrice();
    }

    public static Novel from(
            String id,
            Type type,
            Title title,
            Author author,
            Stock stock,
            PublicationYear publicationYear,
            Price price
    ){
        return new Novel(id, type, title, author, stock, price, publicationYear);
    }

    @Override
    public void calculateIndividualPrice() {
        double currentPrice = this.getPrice().value();
        this.setPrice(new Price(currentPrice * 2));
    }

    @Override
    public Copy findCheapestCopy(List<Copy> copies) {
        return copies.stream()
                .filter(copy -> copy.getType().equals("Novel"))
                .min(Comparator.comparingDouble(copy -> copy.getPrice().value()))
                .orElse(null);
    }
}

