package com.example.digicu_customer.data.dataset;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProposalDataModel implements Serializable {
    @SerializedName("id")
    private Integer proposalId;
    @SerializedName("owner")
    private String owner;
    @SerializedName("trade")
    private Integer tradeId;
    @SerializedName("coupon")
    private CouponDataModel couponDataModel;

    @Override
    public String toString() {
        return "ProposalDataModel{" +
                "proposalId=" + proposalId +
                ", owner='" + owner + '\'' +
                ", tradeId=" + tradeId +
                ", couponId=" + couponDataModel +
                '}';
    }

    public Integer getProposalId() {
        return proposalId;
    }

    public void setProposalId(Integer proposalId) {
        this.proposalId = proposalId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public CouponDataModel getCouponDataModel() {
        return couponDataModel;
    }

    public void setCouponDataModel(CouponDataModel couponDataModel) {
        this.couponDataModel = couponDataModel;
    }
}
