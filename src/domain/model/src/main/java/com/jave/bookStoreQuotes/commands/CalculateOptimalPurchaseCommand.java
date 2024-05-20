package com.jave.bookStoreQuotes.commands;

import com.jave.generic.Command;

import java.util.List;


public class CalculateOptimalPurchaseCommand extends Command {

    private String bookStoreQuoteId;
    private List<ObjectString> copies;
    private double budget;

    public CalculateOptimalPurchaseCommand(){}

    public String getBookStoreQuoteId() {
        return bookStoreQuoteId;
    }

    public void setBookStoreQuoteId(String bookStoreQuoteId) {
        this.bookStoreQuoteId = bookStoreQuoteId;
    }

    public List<ObjectString> getCopies() {
        return copies;
    }

    public void setCopies(List<ObjectString> copies) {
        this.copies = copies;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
