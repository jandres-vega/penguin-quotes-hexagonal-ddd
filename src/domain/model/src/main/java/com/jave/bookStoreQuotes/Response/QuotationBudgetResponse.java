package com.jave.bookStoreQuotes.Response;

import java.util.List;

public class QuotationBudgetResponse {

    private List<CopyResponse> purchasedCopies;
    private int quantityBooks;
    private int quantityNovels;
    private double priceTotalCopiesDiscount;
    private double totalPurchase;

    public QuotationBudgetResponse(List<CopyResponse> purchasedCopies, int quantityBooks, int quantityNovels, double priceTotalCopiesDiscount, double totalPurchase) {
        this.purchasedCopies = purchasedCopies;
        this.quantityBooks = quantityBooks;
        this.quantityNovels = quantityNovels;
        this.priceTotalCopiesDiscount = priceTotalCopiesDiscount;
        this.totalPurchase = totalPurchase;
    }

    public List<CopyResponse> getPurchasedCopies() {
        return purchasedCopies;
    }

    public void setPurchasedCopies(List<CopyResponse> purchasedCopies) {
        this.purchasedCopies = purchasedCopies;
    }

    public int getQuantityBooks() {
        return quantityBooks;
    }

    public void setQuantityBooks(int quantityBooks) {
        this.quantityBooks = quantityBooks;
    }

    public int getQuantityNovels() {
        return quantityNovels;
    }

    public void setQuantityNovels(int quantityNovels) {
        this.quantityNovels = quantityNovels;
    }

    public double getPriceTotalCopiesDiscount() {
        return priceTotalCopiesDiscount;
    }

    public void setPriceTotalCopiesDiscount(double priceTotalCopiesDiscount) {
        this.priceTotalCopiesDiscount = priceTotalCopiesDiscount;
    }

    public double getTotalPurchase() {
        return totalPurchase;
    }

    public void setTotalPurchase(double totalPurchase) {
        this.totalPurchase = totalPurchase;
    }

}
