package com.jave.bookStoreQuotes.entity;


import com.jave.bookStoreQuotes.values.BookStoreQuotes.TotalIncrement;
import com.jave.bookStoreQuotes.values.copy.*;
import com.jave.bookStoreQuotes.values.identities.CopyId;
import com.jave.generic.Entity;

import java.util.Comparator;
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

    public static Copy findCheapest(List<Copy> copies, String type) {
        return copies.stream()
                .filter(copy -> copy.getType().value().equals(type))
                .min(Comparator.comparingDouble(copy -> copy.getPrice().value()))
                .orElse(null);
    }

    public static int countBooks(List<Copy> copies) {
        return (int) copies.stream()
                .filter(copy -> copy.getType().value().equals("Book"))
                .count();
    }

    public static int countNovels(List<Copy> copies) {
        return (int) copies.stream()
                .filter(copy -> copy.getType().value().equals("Novel"))
                .count();
    }

    public static double getDiscountedPrice(double price, int count) {
        if (count > 10) {
            double discount = 0.0015 * (count - 10);
            return price * (1 - discount);
        }
        return price;
    }

    public abstract Copy findCheapestCopy(List<Copy> copies);

    public double appplyIncrementPrice(){
        double basePrice = this.price.value();
        double increment = 0.02;

        return basePrice * (1 + increment);
    }

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

    public Type getType() {return type;}

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

    public String getId(){return super.identity().value();}


}
