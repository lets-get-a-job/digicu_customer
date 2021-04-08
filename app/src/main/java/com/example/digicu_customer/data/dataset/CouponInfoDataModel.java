package com.example.digicu_customer.data.dataset;

public class CouponInfoDataModel {
    // coupon type ex) mileage or stamp
    public enum CouponType {
        MILEAGE,
        STAMP
    }

    private ShopDataModel shopDataModel;
    private CouponType type;
    //if type is MILEAGE
    private int mileage;
    private int percent;
    //if type is STAMP
    private int stampCnt;
    private int countCanBeTransfer;

    public CouponInfoDataModel(ShopDataModel shopDataModel, CouponType type, int mileage, int stampCnt, int countCanBeTransfer) {
        this.shopDataModel = shopDataModel;
        this.type = type;
        this.mileage = mileage;
        this.stampCnt = stampCnt;
        this.countCanBeTransfer = countCanBeTransfer;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "shop=" + shopDataModel +
                ", type=" + type +
                ", mileage=" + mileage +
                ", stampCnt=" + stampCnt +
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

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getStampCnt() {
        return stampCnt;
    }

    public void setStampCnt(int stampCnt) {
        this.stampCnt = stampCnt;
    }

    public int getCountCanBeTransfer() {
        return countCanBeTransfer;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public void setCountCanBeTransfer(int countCanBeTransfer) {
        this.countCanBeTransfer = countCanBeTransfer;
    }
}
