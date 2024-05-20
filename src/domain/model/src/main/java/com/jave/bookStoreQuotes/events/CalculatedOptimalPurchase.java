package com.jave.bookStoreQuotes.events;


import com.jave.bookStoreQuotes.commands.ObjectString;
import com.jave.generic.DomainEvent;

import java.util.List;

public class CalculatedOptimalPurchase extends DomainEvent {

    private String bookStoreQuoteId;
    private List<ObjectString> copies;
    private double budget;
    private double purchase;
    private double total;


    public void setCopies(List<ObjectString> copies) {
        this.copies = copies;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getPurchase() {
        return purchase;
    }

    public void setPurchase(double purchase) {
        this.purchase = purchase;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public CalculatedOptimalPurchase(List<ObjectString> bookIds, double budget) {
        super(TypeEvent.CALCULATED_OPTIMAL_PURCHASE.toString());
        this.copies = bookIds;
        this.budget = budget;
    }

    public CalculatedOptimalPurchase() {}

    public String getBookStoreQuoteId() {
        return bookStoreQuoteId;
    }

    public void setBookStoreQuoteId(String bookStoreQuoteId) {
        this.bookStoreQuoteId = bookStoreQuoteId;
    }

    public List<ObjectString> getIdCopies() {
        return copies;
    }

    public double getBudget() {
        return budget;
    }
}
