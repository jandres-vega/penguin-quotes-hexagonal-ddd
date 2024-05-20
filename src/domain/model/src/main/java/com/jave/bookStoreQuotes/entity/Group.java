package com.jave.bookStoreQuotes.entity;


import com.jave.bookStoreQuotes.values.BookStoreQuotes.TotalPrice;
import com.jave.bookStoreQuotes.values.identities.GroupId;
import com.jave.bookStoreQuotes.values.shared.DiscountApply;
import com.jave.generic.Entity;

import java.util.List;

public class Group extends Entity<GroupId> {

    private TotalPrice totalPrice;
    private List<Groupment> groupments;
    private DiscountApply discountApply;

    public Group(GroupId id) {
        super(id);
    }
}
