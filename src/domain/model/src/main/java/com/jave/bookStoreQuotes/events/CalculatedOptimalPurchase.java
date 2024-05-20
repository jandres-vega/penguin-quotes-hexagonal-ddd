package com.jave.bookStoreQuotes.events;


import com.jave.bookStoreQuotes.commands.ObjectString;
import com.jave.generic.DomainEvent;

import java.time.LocalDate;
import java.util.List;

public class CalculatedOptimalPurchase extends DomainEvent {

    private String bookStoreQuoteId;
    private List<ObjectString> copies;
    private double budget;
    private int quantityBooks;
    private int quantityNovels;
    private double priceTotalCopiesDiscount;
    private double totalPurchase;
    private LocalDate customerCreatedDate;

    public CalculatedOptimalPurchase(List<ObjectString> bookIds, double budget, LocalDate customerCreatedDate) {
        super(TypeEvent.CALCULATED_OPTIMAL_PURCHASE.toString());
        this.copies = bookIds;
        this.budget = budget;
        this.customerCreatedDate = customerCreatedDate;
    }

    public void setCopies(List<ObjectString> copies) {
        this.copies = copies;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public CalculatedOptimalPurchase() {}

    public String getBookStoreQuoteId() {
        return bookStoreQuoteId;
    }

    public void setBookStoreQuoteId(String bookStoreQuoteId) {
        this.bookStoreQuoteId = bookStoreQuoteId;
    }

    public List<ObjectString> getCopies() {
        return copies;
    }

    public double getBudget() {
        return budget;
    }

    public int getQuantityBooks() {
        return quantityBooks;
    }

    public void setQuantityBooks(int quantityBooks) {
        this.quantityBooks = quantityBooks;
    }

    public int getQuantityNovels() {
        return quantityNovels;
    }

    public void setQuantityNovels(int quantityNovels) {
        this.quantityNovels = quantityNovels;
    }

    public double getPriceTotalCopiesDiscount() {
        return priceTotalCopiesDiscount;
    }

    public void setPriceTotalCopiesDiscount(double priceTotalCopiesDiscount) {
        this.priceTotalCopiesDiscount = priceTotalCopiesDiscount;
    }

    public double getTotalPurchase() {
        return totalPurchase;
    }

    public void setTotalPurchase(double totalPurchase) {
        this.totalPurchase = totalPurchase;
    }

    public LocalDate getCustomerCreatedDate() {
        return customerCreatedDate;
    }

    public void setCustomerCreatedDate(LocalDate customerCreatedDate) {
        this.customerCreatedDate = customerCreatedDate;
    }
}
