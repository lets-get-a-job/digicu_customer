package com.example.digicu_customer.dataset;

import java.util.Arrays;
import java.util.Date;

public class RecordOfPurchase {
    private CouponInfo couponInfo;
    private int receiptNumber;
    private Date purchaseDate;
    private Product products[];

    public RecordOfPurchase(CouponInfo couponInfo, int receiptNumber, Date purchaseDate, Product[] products) {
        this.couponInfo = couponInfo;
        this.receiptNumber = receiptNumber;
        this.purchaseDate = purchaseDate;
        this.products = products;
    }

    @Override
    public String toString() {
        return "RecordOfPurchase{" +
                "coupon=" + couponInfo +
                ", receiptNumber=" + receiptNumber +
                ", purchaseDate=" + purchaseDate +
                ", products=" + Arrays.toString(products) +
                '}';
    }

    public CouponInfo getCouponInfo() {
        return couponInfo;
    }

    public void setCouponInfo(CouponInfo couponInfo) {
        this.couponInfo = couponInfo;
    }

    public int getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(int receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public int getTotalPrice() {
        int totalPrice = 0;

        for (Product pr : products) {
            totalPrice+=(pr.getPrice() * pr.getCount());
        }

        return totalPrice;
    }
}
