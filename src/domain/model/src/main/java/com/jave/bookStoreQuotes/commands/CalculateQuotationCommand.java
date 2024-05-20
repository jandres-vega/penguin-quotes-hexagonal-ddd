package com.jave.bookStoreQuotes.commands;

import com.jave.generic.Command;

import java.time.LocalDate;
import java.util.List;

public class CalculateQuotationCommand extends Command {

    private String bookStoreQuoteId;
    private List<ObjectIdsAmount> copies;
    private LocalDate customerCreatedDate;

    public CalculateQuotationCommand(){}

    public String getBookStoreQuoteId() {
        return bookStoreQuoteId;
    }

    public void setBookStoreQuoteId(String bookStoreQuoteId) {
        this.bookStoreQuoteId = bookStoreQuoteId;
    }

    public List<ObjectIdsAmount> getCopies() {
        return copies;
    }

    public void setCopies(List<ObjectIdsAmount> copies) {
        this.copies = copies;
    }

    public LocalDate getCustomerCreatedDate() {
        return customerCreatedDate;
    }

    public void setCustomerCreatedDate(LocalDate customerCreatedDate) {
        this.customerCreatedDate = customerCreatedDate;
    }
}
