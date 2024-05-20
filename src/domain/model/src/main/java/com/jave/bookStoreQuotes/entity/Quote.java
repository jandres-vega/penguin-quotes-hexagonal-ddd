package com.jave.bookStoreQuotes.entity;


import com.jave.bookStoreQuotes.Response.QuotationResponse;
import com.jave.bookStoreQuotes.commands.ObjectIdsAmount;
import com.jave.bookStoreQuotes.helper.Seniority;
import com.jave.bookStoreQuotes.values.copy.Price;
import com.jave.bookStoreQuotes.values.identities.CustomerId;
import com.jave.bookStoreQuotes.values.identities.QuoteId;
import com.jave.bookStoreQuotes.values.quote.Discount;
import com.jave.bookStoreQuotes.values.quote.Total;
import com.jave.generic.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Quote extends Entity<QuoteId> {

    private CustomerId customerId;
    private  List<Copy> copies;
    private Total total;
    private Discount discount;

    private Quote(CustomerId customerId, List<Copy> copies, Total total, Discount discount) {
        super(new QuoteId());
        this.customerId = customerId;
        this.copies = copies;
        this.total = total;
        this.discount = discount;
    }


    public static Quote from(
            CustomerId customerId,
            List<Copy> copies,
            Total total,
            Discount discount
    ){
        return new Quote(customerId, copies, total, discount);
    }

    public QuotationResponse applyWholeSalePurchaseIncrement(List<Copy> copies, LocalDate customerSeniority, List<ObjectIdsAmount> orderItems){
        double totalPriceCopies = 0;
        double discountAccumulated = 0;
        int totalBooks = 0;
        List<Copy> copyOrder = copiesSort(copies);
        for (Copy copy : copyOrder) {
            int amount = orderItems.stream()
                    .filter(item -> item.getId().equals(copy.getId()))
                    .findFirst()
                    .map(ObjectIdsAmount::getAmount)
                    .orElse(0);

            double totalPrice = copy.getPrice().value() * amount;
            totalPriceCopies += totalPrice;
            totalBooks += amount;
        }
        double accumulatedDiscount = 0.0f;
        for (Copy copy : copyOrder) {
            totalBooks += orderItems.stream()
                    .filter(item -> item.getId().equals(copy.getId()))
                    .findFirst()
                    .map(ObjectIdsAmount::getAmount)
                    .orElse(0);

            if (totalBooks > 10) {
                double price = copy.getPrice().value();
                accumulatedDiscount += 0.15f;
                double discount = price * accumulatedDiscount;
                double discountedPrice = price - discount;
                discountAccumulated += discountedPrice;
                copy.setPrice(Price.of(discountedPrice));
                totalPriceCopies += discountedPrice;
            }
        }
        double totalDiscount = Seniority.calculateDiscountSeniority(customerSeniority, totalPriceCopies);
        double totalDiscountAccumulated = totalDiscount + discountAccumulated;
        double totalProcceDiscount = totalPriceCopies - totalDiscountAccumulated;
        return new QuotationResponse(orderItems, totalProcceDiscount, totalDiscountAccumulated);
    }

    public QuotationResponse applyRetailSalePurchaseIncrement(List<Copy> quotations, LocalDate seniority, List<ObjectIdsAmount> orderItems){
        double totalPriceCopies = 0;

        for (Copy copy : quotations) {
            int amount = orderItems.stream()
                    .filter(item -> item.getId().equals(copy.getId()))
                    .findFirst()
                    .map(ObjectIdsAmount::getAmount)
                    .orElse(0);

            double totalIncrement = (copy.getPrice().value() * 0.02);
            double newPrice = copy.getPrice().value() + totalIncrement;
            copy.setPrice(Price.of(newPrice));
            double totalPrice = newPrice * amount;;
            totalPriceCopies += totalPrice;
        }
        double totalDiscount = Seniority.calculateDiscountSeniority(seniority, totalPriceCopies);
        double discountSeniority = totalPriceCopies - totalDiscount;

        return new QuotationResponse(orderItems, totalPriceCopies, totalDiscount == 0.0 ? 0 : discountSeniority);
    }
    public List<Copy> copiesSort(List<Copy> copies) {
        List<Copy> mutableCopies = new ArrayList<>(copies);
        mutableCopies.sort(Comparator.comparingDouble(copy -> copy.getPrice().value()));
        return mutableCopies;
    }

    public boolean isWholesalePurchase(List<ObjectIdsAmount> quotationList) {
        int totalQuantity = quotationList.stream()
                .mapToInt(ObjectIdsAmount::getAmount).sum();
        return totalQuantity > 10;
    }

}