package com.jave.DTO;

import com.jave.bookStoreQuotes.commands.ObjectIdsAmount;


import java.time.LocalDate;
import java.util.List;

public class QuotationCreatedDto {

    private final String bookStoreQuoteId;
    private final List<ObjectIdsAmount> orderItems;
    private final double totalQuotation;
    private final double discountTotal;
    private final LocalDate customerCreatedDate;

    public QuotationCreatedDto(String bookStoreQuoteId, List<ObjectIdsAmount> orderItems, double totalQuotation, double discountTotal, LocalDate customerCreatedDate) {

        this.bookStoreQuoteId = bookStoreQuoteId;
        this.orderItems = orderItems;
        this.totalQuotation = totalQuotation;
        this.discountTotal = discountTotal;
        this.customerCreatedDate = customerCreatedDate;
    }


    public String getBookStoreQuoteId() {
        return bookStoreQuoteId;
    }

    public List<ObjectIdsAmount> getOrderItems() {
        return orderItems;
    }

    public double getTotalQuotation() {
        return totalQuotation;
    }

    public double getDiscountTotal() {
        return discountTotal;
    }

    public LocalDate getCustomerCreatedDate() {
        return customerCreatedDate;
    }
}
