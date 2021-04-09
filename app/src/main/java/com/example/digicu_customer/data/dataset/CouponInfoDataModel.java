package com.example.digicu_customer.data.dataset;

import java.io.Serializable;

public class CouponInfoDataModel implements Serializable {
    // coupon type ex) mileage or stamp
    public enum CouponType {
        MILEAGE,
        STAMP
    }

    private ShopDataModel shopDataModel;
    private CouponType type;
    //if type is MILEAGE
    private double percent;
    //if type is STAMP
    private int countCanBeTransfer;

    public CouponInfoDataModel(ShopDataModel shopDataModel, CouponType type, int countCanBeTransfer) {
        this.shopDataModel = shopDataModel;
        this.type = type;
        this.countCanBeTransfer = countCanBeTransfer;
        this.percent = 0.05;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "shop=" + shopDataModel +
                ", type=" + type +
                ", countCanBeTransfer=" + countCanBeTransfer +
                '}';
    }

    public ShopDataModel getShopDataModel() {
        return shopDataModel;
    }

    public void setShopDataModel(ShopDataModel shopDataModel) {
        this.shopDataModel = shopDataModel;
    }

    public CouponType getType() {
        return type;
    }

    public void setType(CouponType type) {
        this.type = type;
    }

    public int getCountCanBeTransfer() {
        return countCanBeTransfer;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public void setCountCanBeTransfer(int countCanBeTransfer) {
        this.countCanBeTransfer = countCanBeTransfer;
    }
}
