package com.jave.bookStoreQuotes.entity;

import com.jave.bookStoreQuotes.values.copy.*;
import java.util.Comparator;
import java.util.List;


public class Book extends Copy{
    public Book(String id, Type type, Title title, Author author, Stock stock, Price price, PublicationYear publicationYear) {
        super(id, type, title, author, stock, price, publicationYear);
        calculateIndividualPrice();
    }

    public static Book from(
            String id,
            Type type,
            Title title,
            Author author,
            Stock stock,
            PublicationYear publicationYear,
            Price price
    ){
        return new Book( id, type, title, author, stock, price, publicationYear);
    }

    @Override
    public void calculateIndividualPrice() {
        double currentPrice = this.getPrice().value();
        double newPrice = currentPrice * (1 + 1.0 / 3.0);
        this.setPrice(new Price(newPrice));
    }

    @Override
    public Copy findCheapestCopy(List<Copy> copies) {
        return copies.stream()
                .filter(copy -> copy.getType().equals("Book"))
                .min(Comparator.comparingDouble(copy -> copy.getPrice().value()))
                .orElse(null);
    }
}
