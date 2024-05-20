package com.jave.bookStoreQuotes.events;



import com.jave.bookStoreQuotes.commands.ObjectIdsAmount;
import com.jave.generic.DomainEvent;

import java.time.LocalDate;
import java.util.List;

public class QuotationCreated extends DomainEvent {

    private String bookStoreQuoteId;

    private List<ObjectIdsAmount> orderItems;

    private double totalQuotation;

    private double discountTotal;

    private LocalDate customerCreatedDate;

    public QuotationCreated(List<ObjectIdsAmount> orderItems, LocalDate customerCreatedDate) {
        super(TypeEvent.QUOTATION_CREATED.toString());
        this.orderItems = orderItems;
        this.customerCreatedDate= customerCreatedDate;

    }

    public LocalDate getCustomerCreatedDate() {
        return customerCreatedDate;
    }

    public void setCustomerCreatedDate(LocalDate customerCreatedDate) {
        this.customerCreatedDate = customerCreatedDate;
    }

    public QuotationCreated() {
    }

    public List<ObjectIdsAmount> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<ObjectIdsAmount> orderItems) {
        this.orderItems = orderItems;
    }

    public double getTotalQuotation() {
        return totalQuotation;
    }

    public void setTotalQuotation(double totalQuotation) {
        this.totalQuotation = totalQuotation;
    }

    public double getDiscountTotal() {
        return discountTotal;
    }

    public void setDiscountTotal(double discountTotal) {
        this.discountTotal = discountTotal;
    }

    public String getBookStoreQuoteId() {
        return bookStoreQuoteId;
    }

    public void setBookStoreQuoteId(String bookStoreQuoteId) {
        this.bookStoreQuoteId = bookStoreQuoteId;
    }
}
