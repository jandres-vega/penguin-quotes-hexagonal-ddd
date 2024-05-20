package com.jave.bookStoreQuotes;


import com.jave.bookStoreQuotes.commands.ObjectString;
import com.jave.bookStoreQuotes.entity.Copy;
import com.jave.bookStoreQuotes.events.BookSaved;
import com.jave.bookStoreQuotes.events.CalculatedOptimalPurchase;
import com.jave.bookStoreQuotes.events.CreatedRoot;
import com.jave.bookStoreQuotes.factory.CopyFactory;
import com.jave.bookStoreQuotes.factory.CopyFactoryImpl;
import com.jave.bookStoreQuotes.values.BookStoreQuotes.TotalPrice;
import com.jave.bookStoreQuotes.values.copy.*;
import com.jave.generic.EventChange;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookStoreQuotesEventChange extends EventChange {

    private final CopyFactory copyFactory = new CopyFactoryImpl();

    public BookStoreQuotesEventChange(BookStoreQuotes bookStoreQuotes) {
        apply((CreatedRoot event) -> {
            bookStoreQuotes.copies = new ArrayList<>();
        });
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
            List<Copy> copies = bookStoreQuotes.copies;
            List<Copy> replacementCopies = new ArrayList<>();

            for (Copy copy : copies) {
                for (ObjectString objectString : event.getIdCopies()) {
                    if (copy.getId().equals(objectString.getId())) {
                        replacementCopies.add(copy);
                    }
                }
            }
            System.out.println(copies.get(0).getId());
            System.out.println("sise                   " + replacementCopies.size());
//            if (replacementCopies.isEmpty()) {
//                throw new IllegalArgumentException("The optimal purchase must have at least 11 copies");
//            }
            List<Copy> optimaCopiesSelected = selectOptimalCopies(replacementCopies, event.getBudget(), calculateMaxDiscount(copies), event);
            double totalCost = getTotalCost(optimaCopiesSelected);
            bookStoreQuotes.totalPrice = TotalPrice.of(totalCost);

            event.setTotal(totalCost);
            event.setPurchase(totalCost);
            event.setCopies(optimaCopiesSelected.stream().map(copy -> new ObjectString(copy.getId())).toList());
        });
    }

    public Copy findCheapestBookOrNovel(List<Copy> copies) {
        return copies.stream()
                .filter(copy -> copy.getType().equals("Book") || copy.getType().equals("Novel"))
                .min(Comparator.comparingDouble(copy -> copy.getPrice().value()))
                .orElse(null);
    }

    private List<Copy> selectOptimalCopies(List<Copy> copies, double budget, double maxDiscount, CalculatedOptimalPurchase event) {
        List<Copy> optimalCopies = new ArrayList<>();
        double remainingBudget = budget;
        double discountedPrice = 0;

        for (Copy copy : copies) {
            discountedPrice = copy.getPrice().value() * (1 - maxDiscount);
            if (discountedPrice <= remainingBudget) {
                optimalCopies.add(copy);
                remainingBudget -= discountedPrice;
                event.setBudget(remainingBudget);
            } else {
                break;
            }
        }
        System.out.println(optimalCopies.size());
//        if (optimalCopies.size() < 11) {
//            throw new IllegalArgumentException("The optimal purchase must have at least 11 copies");
//        }

        List<Copy> copiesTest = selectCopies(optimalCopies, budget);

        System.out.println(copiesTest.size());

        event.setTotal(discountedPrice);
        return optimalCopies;
    }

    private double getTotalCost(List<Copy> copies) {
        return copies.stream()
                .mapToDouble(copy -> copy.getPrice().value())
                .sum();
    }

    private double calculateMaxDiscount(List<Copy> copies) {
        if (copies.isEmpty()) {
            return 0;
        }

        int count = copies.size();
        double maxDiscount = 0;
        double totalCost = 0;

        for (int i = 0; i < count; i++) {
            Copy copy = copies.get(i);
            double discount = i >= 10 ? 0.0015 * (count - 10) : 0;
            double cost = copy.getPrice().value() * (1 - discount);
            totalCost += cost;
        }

        maxDiscount = 1 - (totalCost / copies.stream()
                .mapToDouble(copy -> copy.getPrice()
                        .value()).sum());
        return maxDiscount;
    }

    public List<Copy> selectCopies(List<Copy> copies, double budget) {
        List<Copy> selectedCopies = new ArrayList<>();
        Copy cheapestCopy = findCheapestBookOrNovel(copies);
        if (cheapestCopy == null) {
            return selectedCopies;
        }
        selectedCopies.add(cheapestCopy);
        budget -= cheapestCopy.getPrice().value();

        String otherType = cheapestCopy.getType().equals("Book") ? "Novel" : "Book";
        Copy otherCopy = copies.stream()
                .filter(copy -> copy.getType().equals(otherType))
                .findFirst()
                .orElse(null);
        if (otherCopy != null) {
            selectedCopies.add(otherCopy);
            budget -= otherCopy.getPrice().value();
        }

        for (Copy copy : copies) {
            if (budget <= 0) {
                break;
            }
            if (copy.getPrice().value() <= budget) {
                selectedCopies.add(copy);
                budget -= copy.getPrice().value();
            }
        }
        return selectedCopies;
    }
}

