package com.example.digicu_customer.data.dataset;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class CouponTradeRegisterDataModel implements Serializable {

    @SerializedName("coupon_id")
    private int couponId;
    @SerializedName("bound_value")
    private int boundValue;

    public CouponTradeRegisterDataModel(int couponId, int boundValue) {
        this.couponId = couponId;
        this.boundValue = boundValue;
    }

    @Override
    public String toString() {
        return "TradeDataModel{" +
                "couponId=" + couponId +
                ", boundValue=" + boundValue +
                '}';
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public int getBoundValue() {
        return boundValue;
    }

    public void setBoundValue(int boundValue) {
        this.boundValue = boundValue;
    }
}
