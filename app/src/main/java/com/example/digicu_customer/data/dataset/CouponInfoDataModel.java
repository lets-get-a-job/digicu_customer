package com.example.digicu_customer.data.dataset;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CouponInfoDataModel implements Serializable {
    @SerializedName("id")
    private int couponId;
    @SerializedName("name")
    private String name;
    @SerializedName("goal")
    private int goal;
    @SerializedName("type")
    private CouponType couponType;
    @SerializedName("period")
    private int period;
    @SerializedName("value")
    private int value;
    // coupon type ex) mileage or stamp
    public enum CouponType {
        DISCOUNT,
    }

    private ShopDataModel shopDataModel;

    public CouponInfoDataModel(ShopDataModel shopDataModel, int couponId, String name, int goal, String couponType, int period, int value) {
        this.shopDataModel = shopDataModel;
        this.couponId = couponId;
        this.name = name;
        this.goal = goal;

        if(couponType.equals("DISCOUNT"))
            this.couponType = CouponType.DISCOUNT;

        this.period = period;
        this.value = value;
    }

    public ShopDataModel getShopDataModel() {
        return shopDataModel;
    }

    public void setShopDataModel(ShopDataModel shopDataModel) {
        this.shopDataModel = shopDataModel;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public CouponType getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        if(couponType.equals("DISCOUNT"))
            this.couponType = CouponType.DISCOUNT;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
