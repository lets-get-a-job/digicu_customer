package com.example.digicu_customer.data.dataset;

import java.util.Date;
import java.util.List;

public class RecordOfPurchaseDataModel {
    private CouponInfoDataModel couponInfoDataModel;
    private int receiptNumber;
    private Date purchaseDate;
    private List<ProductDataModel> productDataModels;

    public RecordOfPurchaseDataModel(CouponInfoDataModel couponInfoDataModel, int receiptNumber, Date purchaseDate, List<ProductDataModel> productDataModels) {
        this.couponInfoDataModel = couponInfoDataModel;
        this.receiptNumber = receiptNumber;
        this.purchaseDate = purchaseDate;
        this.productDataModels = productDataModels;
    }

    @Override
    public String toString() {
        return "RecordOfPurchaseDataModel{" +
                "couponInfoDataModel=" + couponInfoDataModel +
                ", receiptNumber=" + receiptNumber +
                ", purchaseDate=" + purchaseDate +
                ", productDataModels=" + productDataModels +
                '}';
    }

    public CouponInfoDataModel getCouponInfoDataModel() {
        return couponInfoDataModel;
    }

    public void setCouponInfoDataModel(CouponInfoDataModel couponInfoDataModel) {
        this.couponInfoDataModel = couponInfoDataModel;
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

    public List<ProductDataModel> getProductDataModels() {
        return productDataModels;
    }

    public void setProductDataModels(List<ProductDataModel> productDataModels) {
        this.productDataModels = productDataModels;
    }

    public int getTotalPrice() {
        int totalPrice = 0;

        for (ProductDataModel pr : productDataModels) {
            totalPrice+=(pr.getPrice() * pr.getCount());
        }

        return totalPrice;
    }
}
