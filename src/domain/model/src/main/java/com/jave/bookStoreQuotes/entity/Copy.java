package com.jave.bookStoreQuotes.entity;


import com.jave.bookStoreQuotes.values.BookStoreQuotes.TotalIncrement;
import com.jave.bookStoreQuotes.values.copy.*;
import com.jave.bookStoreQuotes.values.identities.CopyId;
import com.jave.generic.Entity;

import java.util.List;

public abstract class Copy extends Entity<CopyId> {

    private final Title title;
    private final Author author;
    private final Stock stock;
    private final PublicationYear publicationYear;
    private Price price;
    private Type type;
    private TotalIncrement increment;
    private DiscountMayor discountMayor;
    private DiscountCustomer discountCustomer;

    public Copy(String id, Type type, Title title, Author author, Stock stock, Price price, PublicationYear publicationYear) {
        super(new CopyId(id));
        this.title = title;
        this.type = type;
        this.author = author;
        this.stock = stock;
        this.price = price;
        this.publicationYear = publicationYear;
    }

    public abstract void calculateIndividualPrice();

    public abstract Copy findCheapestCopy(List<Copy> copies);

    public void applyDiscount(){}

    public void appplyIncrementPrice(){}

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Title getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Stock getStock() {
        return stock;
    }

    public PublicationYear getPublicationYear() {
        return publicationYear;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public TotalIncrement getIncrement() {
        return increment;
    }

    public void setIncrement(TotalIncrement increment) {
        this.increment = increment;
    }

    public DiscountMayor getDiscountMayor() {
        return discountMayor;
    }

    public void setDiscountMayor(DiscountMayor discountMayor) {
        this.discountMayor = discountMayor;
    }

    public DiscountCustomer getDiscountCustomer() {
        return discountCustomer;
    }

    public void setDiscountCustomer(DiscountCustomer discountCustomer) {
        this.discountCustomer = discountCustomer;
    }

    public String getId(){
        return super.identity().value();
    }

}
