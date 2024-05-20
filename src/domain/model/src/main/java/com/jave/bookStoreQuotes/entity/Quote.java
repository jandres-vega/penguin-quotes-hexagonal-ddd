package com.jave.bookStoreQuotes.entity;


import com.jave.bookStoreQuotes.values.BookStoreQuotes.TotalIncrement;
import com.jave.bookStoreQuotes.values.copy.Price;
import com.jave.bookStoreQuotes.values.identities.CustomerId;
import com.jave.bookStoreQuotes.values.identities.QuoteId;
import com.jave.bookStoreQuotes.values.quote.Discount;
import com.jave.bookStoreQuotes.values.quote.Total;
import com.jave.generic.Entity;

import java.util.List;

public class Quote extends Entity<QuoteId> {

    private final CustomerId customerId;
    private final List<Copy> copies;
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

    public void applyWholeSalePurchaseIncrement(){}

    public void applyRetailSalePurchaseIncrement(){
        double totalPriceCopies = 0;
        for (Copy copy : copies ){
            double totalIncrement = (copy.getPrice().value() * 0.02);
            copy.setPrice(Price.of(copy.getPrice().value() + totalIncrement));
            copy.setIncrement(TotalIncrement.of(copy.getPrice().value() * copy.getStock().value()));
            totalPriceCopies += copy.getIncrement().value();
        }
        this.total = new Total(totalPriceCopies);
        this.discount = new Discount(0.1);
    }

    public void appyDiscount(){}

}