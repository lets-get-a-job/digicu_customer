package com.example.digicu_customer.dataset;

import java.util.Date;

public class Coupon {
    private Shop shop;
    // 쿠폰 일련번호
    private int couponKey;
    private String name;
    private Date expirationDate;
    private boolean available;

    public Coupon(Shop shop, int couponKey, String name, Date expirationDate, boolean available) {
        this.shop = shop;
        this.couponKey = couponKey;
        this.name = name;
        this.expirationDate = expirationDate;
        this.available = available;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "shop=" + shop +
                ", couponNum=" + couponKey +
                ", name='" + name + '\'' +
                ", expirationDate=" + expirationDate +
                ", available=" + available +
                '}';
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public int getCouponNum() {
        return couponKey;
    }

    public void setCouponNum(int couponKey) {
        this.couponKey = couponKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
