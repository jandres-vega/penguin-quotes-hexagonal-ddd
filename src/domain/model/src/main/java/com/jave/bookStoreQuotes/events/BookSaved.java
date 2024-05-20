package com.jave.bookStoreQuotes.events;


import com.jave.generic.DomainEvent;

public class BookSaved extends DomainEvent {

    private String id;
    private String bookstoreQuoteId;
    private String title;
    private String author;
    private Integer stock;
    private Integer publicationYear;
    private Double price;
    private String type;

    public BookSaved(String title, String author, Integer stock, Integer publicationYear, Double price, String type) {
        super(TypeEvent.BOOK_SAVED.toString());
        this.title = title;
        this.author = author;
        this.stock = stock;
        this.publicationYear = publicationYear;
        this.price = price;
        this.type = type;
    }
    public BookSaved() {
        super(TypeEvent.BOOK_SAVED.toString());
    }

    public String getBookstoreQuoteId() {
        return bookstoreQuoteId;
    }

    public void setBookstoreQuoteId(String bookstoreQuoteId) {
        this.bookstoreQuoteId = bookstoreQuoteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCopyType() {
        return type;
    }

    public void setCopyType(String copyType) {
        this.type = copyType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
