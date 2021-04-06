package com.example.digicu_customer.dataset;

public class CouponInfo {
    // coupon type ex) mileage or stamp
    public enum CouponType {
        MILEAGE,
        STAMP
    }

    private Shop shop;
    private CouponType type;
    //if type is MILEAGE
    private int mileage;
    private int percent;
    //if type is STAMP
    private int stampCnt;
    private int countCanBeTransfer;

    public CouponInfo(Shop shop, CouponType type, int mileage, int stampCnt, int countCanBeTransfer) {
        this.shop = shop;
        this.type = type;
        this.mileage = mileage;
        this.stampCnt = stampCnt;
        this.countCanBeTransfer = countCanBeTransfer;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "shop=" + shop +
                ", type=" + type +
                ", mileage=" + mileage +
                ", stampCnt=" + stampCnt +
                ", countCanBeTransfer=" + countCanBeTransfer +
                '}';
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
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
