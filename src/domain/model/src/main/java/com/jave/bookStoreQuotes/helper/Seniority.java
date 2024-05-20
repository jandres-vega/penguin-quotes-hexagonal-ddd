package com.jave.bookStoreQuotes.helper;

import java.time.LocalDate;
import java.time.Period;

public class Seniority {

    public static double calculateDiscountSeniority(LocalDate customerCreatedDate, double totalPrice) {
        int customerSeniority = calculateSeniority(customerCreatedDate);
        double discountRate = getDiscountRate(customerSeniority);
        return discountRate * totalPrice;
    }

    private static int calculateSeniority(LocalDate customerCreatedDate) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(customerCreatedDate, currentDate);
        return Math.min(period.getYears(), 2);
    }

    private static float getDiscountRate(int customerSeniority) {
        return customerSeniority == 0 ? 0.0f : (customerSeniority == 1 ? 0.12f : 0.17f);
    }
}
