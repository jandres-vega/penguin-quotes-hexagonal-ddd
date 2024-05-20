package com.jave.bookStoreQuotes.Response;

public class CopyResponse {
    private String id;
    private String title;
    private String type;
    private double price;

    public CopyResponse(String id, String title, String type, double price) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
