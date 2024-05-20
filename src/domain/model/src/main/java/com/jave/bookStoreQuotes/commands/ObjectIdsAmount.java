package com.jave.bookStoreQuotes.commands;

public class ObjectIdsAmount {

    private String id;

    private Integer amount;

    public ObjectIdsAmount(String id_book, Integer quantity) {
        this.id = id_book;
        this.amount = quantity;
    }
    public ObjectIdsAmount(){}

    public String getId() {
        return id ;
    }

    public void setId(String id_book) {
        this.id = id_book;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setQuantity(Integer amount) {
        this.amount = amount;
    }
}
