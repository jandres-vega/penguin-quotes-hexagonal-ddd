package com.jave.bookStoreQuotes.Response;

import com.jave.bookStoreQuotes.commands.ObjectIdsAmount;

import java.util.List;

public class QuotationResponse {

    private List<ObjectIdsAmount> orderItems;

    private double totalQuotation;

    private double discountTotal;

    public QuotationResponse(List<ObjectIdsAmount> orderItems, double totalQuotation, double discountTotal) {
        this.orderItems = orderItems;
        this.totalQuotation = totalQuotation;
        this.discountTotal = discountTotal;
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
}
