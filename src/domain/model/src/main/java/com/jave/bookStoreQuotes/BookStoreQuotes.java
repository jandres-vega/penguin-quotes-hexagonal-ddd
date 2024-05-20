package com.jave.bookStoreQuotes;


import com.jave.bookStoreQuotes.commands.ObjectString;
import com.jave.bookStoreQuotes.entity.Copy;
import com.jave.bookStoreQuotes.entity.Quote;
import com.jave.bookStoreQuotes.events.BookSaved;
import com.jave.bookStoreQuotes.events.CalculatedOptimalPurchase;
import com.jave.bookStoreQuotes.events.CreatedRoot;
import com.jave.bookStoreQuotes.values.BookStoreQuotes.CustomerRegistrationDate;
import com.jave.bookStoreQuotes.values.BookStoreQuotes.TotalDiscount;
import com.jave.bookStoreQuotes.values.BookStoreQuotes.TotalIncrement;
import com.jave.bookStoreQuotes.values.BookStoreQuotes.TotalPrice;
import com.jave.bookStoreQuotes.values.copy.*;
import com.jave.bookStoreQuotes.values.identities.BookStoreQuoteId;
import com.jave.generic.AggregateRoot;
import com.jave.generic.DomainEvent;

import java.util.List;

public class BookStoreQuotes extends AggregateRoot<BookStoreQuoteId> {

    protected Copy copy;
    protected TotalPrice totalPrice;
    protected TotalDiscount totalDiscount;
    protected TotalIncrement totalIncrement;
    protected CustomerRegistrationDate customerRegistrationDate;
    protected List<Copy> copies;
    protected Quote quotes;
    protected Copy result;

    public BookStoreQuotes() {
        super(new BookStoreQuoteId());
        subscribe(new BookStoreQuotesEventChange(this));
        appendChange(new CreatedRoot()).apply();
    }

    public BookStoreQuotes(Title title,
                           Author author,
                           Stock stock,
                           PublicationYear publicationYear,
                           Price price,
                           Type type) {
        super(new BookStoreQuoteId());
        subscribe(new BookStoreQuotesEventChange(this));
        appendChange(new BookSaved(
                title.value(),
                author.value(),
                stock.value(),
                publicationYear.value(),
                price.value(), type.value())).apply();
    }

    public BookStoreQuotes(BookStoreQuoteId bookStoreQuoteId) {
        super(bookStoreQuoteId);
        subscribe(new BookStoreQuotesEventChange(this));
    }

    public void addCopy(Title title,
                        Author author,
                        Stock stock,
                        PublicationYear publicationYear,
                        Price price,
                        Type type) {
        appendChange(new BookSaved(

                title.value(),
                author.value(),
                stock.value(),
                publicationYear.value(),
                price.value(), type.value())).apply();
    }

    public void calculateOptimalPurchase(List<ObjectString> copies, double budget) {
        appendChange(new CalculatedOptimalPurchase(copies, budget)).apply();
    }


    public static BookStoreQuotes from(BookStoreQuoteId bookStoreQuoteId, List<DomainEvent> events) {
        var bookStoreQuotes = new BookStoreQuotes(bookStoreQuoteId);
        events.forEach(bookStoreQuotes::applyEvent);
        return bookStoreQuotes;
    }

    public List<Copy> getCopies() {
        return copies;
    }

}
