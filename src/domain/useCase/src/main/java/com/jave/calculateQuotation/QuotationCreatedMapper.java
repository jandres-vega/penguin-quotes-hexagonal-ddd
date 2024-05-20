package com.jave.calculateQuotation;


import com.jave.DTO.QuotationCreatedDto;
import com.jave.bookStoreQuotes.events.QuotationCreated;
import com.jave.generic.DomainEventMapper;

public class QuotationCreatedMapper implements DomainEventMapper<QuotationCreated, QuotationCreatedDto> {
    @Override
    public QuotationCreatedDto map(QuotationCreated domainEvent) {
        return new QuotationCreatedDto(
                domainEvent.getBookStoreQuoteId(),
                domainEvent.getOrderItems(),
                domainEvent.getTotalQuotation(),
                domainEvent.getDiscountTotal(),
                domainEvent.getCustomerCreatedDate()
        );
    }
}
