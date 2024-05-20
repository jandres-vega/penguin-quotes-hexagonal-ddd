package com.jave.bookStoreQuotes.entity;



import com.jave.bookStoreQuotes.values.BookStoreQuotes.TotalPrice;
import com.jave.bookStoreQuotes.values.ProductId;
import com.jave.bookStoreQuotes.values.identities.GroupmenId;
import com.jave.bookStoreQuotes.values.shared.DiscountApply;
import com.jave.generic.Entity;

import java.util.List;

public class Groupment extends Entity<GroupmenId> {

    private List<ProductId> productIds;
    private TotalPrice totalPrice;
    private DiscountApply discountApply;

    public Groupment(GroupmenId id) {
        super(id);
    }

    private Groupment(List<ProductId> productIds, TotalPrice totalPrice, DiscountApply discountApply) {
        super(new GroupmenId());
        this.productIds = productIds;
        this.totalPrice = totalPrice;
        this.discountApply = discountApply;
    }
}
