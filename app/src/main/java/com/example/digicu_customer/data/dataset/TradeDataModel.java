package com.example.digicu_customer.data.dataset;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class TradeDataModel implements Serializable {
    @SerializedName("id")
    private int tradeId;
    @SerializedName("owner")
    private String owner;
    @SerializedName("boundValue")
    private int boundValue;
    @SerializedName("createdDate")
    private Date createdDate;

    public TradeDataModel(int tradeId, String owner, int boundValue, Date createdDate) {
        this.tradeId = tradeId;
        this.owner = owner;
        this.boundValue = boundValue;
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "CouponTradeRegisterDataModel{" +
                "couponId=" + tradeId +
                ", owner='" + owner + '\'' +
                ", boundValue=" + boundValue +
                ", createdDate=" + createdDate +
                '}';
    }

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getBoundValue() {
        return boundValue;
    }

    public void setBoundValue(int boundValue) {
        this.boundValue = boundValue;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
