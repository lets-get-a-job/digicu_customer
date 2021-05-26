package com.example.digicu_customer.data.dataset;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProposalRequestDataModel implements Serializable {
    @SerializedName("trade_id")
    private Integer tradeId;
    @SerializedName("my_coupon_id")
    private Integer myCouponId;

    public ProposalRequestDataModel(Integer tradeId, Integer myCouponId) {
        this.tradeId = tradeId;
        this.myCouponId = myCouponId;
    }

    @Override
    public String toString() {
        return "ProposalRequestDataModel{" +
                "tradeId=" + tradeId +
                ", myCouponId=" + myCouponId +
                '}';
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public Integer getMyCouponId() {
        return myCouponId;
    }

    public void setMyCouponId(Integer myCouponId) {
        this.myCouponId = myCouponId;
    }
}
