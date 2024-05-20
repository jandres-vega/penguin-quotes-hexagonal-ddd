package com.jave.bookStoreQuotes;


import com.jave.bookStoreQuotes.Response.CopyResponse;
import com.jave.bookStoreQuotes.Response.QuotationBudgetResponse;
import com.jave.bookStoreQuotes.Response.QuotationResponse;
import com.jave.bookStoreQuotes.commands.ObjectString;
import com.jave.bookStoreQuotes.entity.Copy;
import com.jave.bookStoreQuotes.entity.Quote;
import com.jave.bookStoreQuotes.events.BookSaved;
import com.jave.bookStoreQuotes.events.CalculatedOptimalPurchase;
import com.jave.bookStoreQuotes.events.CreatedRoot;
import com.jave.bookStoreQuotes.events.QuotationCreated;
import com.jave.bookStoreQuotes.factory.CopyFactory;
import com.jave.bookStoreQuotes.factory.CopyFactoryImpl;
import com.jave.bookStoreQuotes.helper.Seniority;
import com.jave.bookStoreQuotes.values.BookStoreQuotes.TotalPrice;
import com.jave.bookStoreQuotes.values.copy.*;
import com.jave.bookStoreQuotes.values.identities.CustomerId;
import com.jave.bookStoreQuotes.values.quote.Discount;
import com.jave.bookStoreQuotes.values.quote.Total;
import com.jave.generic.EventChange;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class BookStoreQuotesEventChange extends EventChange {
    private final CopyFactory copyFactory = new CopyFactoryImpl();

    public BookStoreQuotesEventChange(BookStoreQuotes bookStoreQuotes) {
        apply((CreatedRoot event) -> bookStoreQuotes.copies = new ArrayList<>());

        apply((BookSaved event) -> {
            Copy copy = copyFactory.createCopy(
                    event.uuid.toString(),
                    event.getCopyType(),
                    new Title(event.getTitle()),
                    new Author(event.getAuthor()),
                    new Stock(event.getStock()),
                    new PublicationYear(event.getPublicationYear()), new Price(event.getPrice()));
            event.setPrice(copy.getPrice().value());
            bookStoreQuotes.copies.add(copy);
        });

        apply((CalculatedOptimalPurchase event) -> {
            List<Copy> copies = event.getCopies().stream()
                    .map(obj -> bookStoreQuotes.getCopies().stream()
                            .filter(c -> c.getId().equals(obj.getId()))
                            .findFirst()
                            .orElse(null))
                    .filter(Objects::nonNull)
                    .toList();

            Copy cheapestBook = Copy.findCheapest(copies, "Book");
            Copy cheapestNovel = Copy.findCheapest(copies, "Novel");

            List<ObjectString> idCopies = new ArrayList<>();

            idCopies.add(new ObjectString(cheapestBook.getId()));
            idCopies.add(new ObjectString(cheapestNovel.getId()));
            event.setCopies(idCopies);

            Copy cheapestCopy = cheapestBook.getPrice().value() < cheapestNovel.getPrice().value() ? cheapestBook : cheapestNovel;
            List<Copy> purchasedCopies = new ArrayList<>();
            purchasedCopies.add(cheapestBook);
            purchasedCopies.add(cheapestNovel);
            double remainingBudget = event.getBudget() - cheapestBook.getPrice().value() - cheapestNovel.getPrice().value();

            int count = 2;
            double total = 0.0;
            while (remainingBudget >= Copy.getDiscountedPrice(cheapestCopy.getPrice().value(), count)) {
                purchasedCopies.add(cheapestCopy);
                double price = Copy.getDiscountedPrice(cheapestCopy.getPrice().value(), count);
                total += price;
                remainingBudget -= price;
                count++;
            }
            event.setBudget(remainingBudget);

            int quantityBooks = Copy.countBooks(purchasedCopies);
            int quantityNovels = Copy.countNovels(purchasedCopies);

            double discountRate = Seniority.calculateDiscountSeniority(event.getCustomerCreatedDate(), total);

            QuotationBudgetResponse response = new QuotationBudgetResponse(
                    purchasedCopies.stream()
                            .map(c -> new CopyResponse(c.getId(), c.getTitle().value(), c.getType().value(), c.getPrice().value()))
                            .toList(),
                    quantityBooks,
                    quantityNovels,
                    total,
                    discountRate
            );
            event.setQuantityBooks(response.getQuantityBooks());
            event.setQuantityNovels(response.getQuantityNovels());
            event.setPriceTotalCopiesDiscount(response.getPriceTotalCopiesDiscount());
            event.setTotalPurchase(response.getTotalPurchase());
        });
        apply((QuotationCreated event) ->{

            Quote quote = Quote.from(
                    new CustomerId(event.uuid.toString()),
                    bookStoreQuotes.getCopies(),
                    new Total(0.0),
                    new Discount(0.0)
            );

            List<Copy> copies = event.getOrderItems().stream()
                    .map(obj -> bookStoreQuotes.getCopies().stream()
                            .filter(c -> c.getId().equals(obj.getId()))
                            .findFirst()
                            .orElse(null))
                    .filter(Objects::nonNull)
                    .toList();

            if(!quote.isWholesalePurchase(event.getOrderItems())){

                QuotationResponse quotation = quote.applyRetailSalePurchaseIncrement(copies, event.getCustomerCreatedDate(), event.getOrderItems());
                bookStoreQuotes.quotes = Quote.from(
                        new CustomerId(event.uuid.toString()),
                        bookStoreQuotes.getCopies(),
                        new Total(quotation.getTotalQuotation()),
                        new Discount(quotation.getDiscountTotal())
                );
                event.setTotalQuotation(quotation.getTotalQuotation());
                event.setDiscountTotal(quotation.getDiscountTotal());
            }else {
                QuotationResponse quotation =  quote.applyWholeSalePurchaseIncrement(copies, event.getCustomerCreatedDate(), event.getOrderItems());
                bookStoreQuotes.quotes = Quote.from(
                        new CustomerId(event.uuid.toString()),
                        bookStoreQuotes.getCopies(),
                        new Total(quotation.getTotalQuotation()),
                        new Discount(quotation.getDiscountTotal())
                );
                event.setTotalQuotation(quotation.getTotalQuotation());
                event.setDiscountTotal(quotation.getDiscountTotal());
            }
        });
    }
}

