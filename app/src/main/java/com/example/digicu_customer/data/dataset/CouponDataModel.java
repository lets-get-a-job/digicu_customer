package com.example.digicu_customer.data.dataset;

import java.io.Serializable;
import java.util.Date;

public class CouponDataModel implements Serializable {
    private ShopDataModel shopDataModel;
    // 쿠폰 일련번호
    private int couponKey;
    private String name;
    private Date expirationDate;
    private boolean available;

    public CouponDataModel(ShopDataModel shopDataModel, int couponKey, String name, Date expirationDate, boolean available) {
        this.shopDataModel = shopDataModel;
        this.couponKey = couponKey;
        this.name = name;
        this.expirationDate = expirationDate;
        this.available = available;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "shop=" + shopDataModel +
                ", couponNum=" + couponKey +
                ", name='" + name + '\'' +
                ", expirationDate=" + expirationDate +
                ", available=" + available +
                '}';
    }

    public ShopDataModel getShopDataModel() {
        return shopDataModel;
    }

    public void setShopDataModel(ShopDataModel shopDataModel) {
        this.shopDataModel = shopDataModel;
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
