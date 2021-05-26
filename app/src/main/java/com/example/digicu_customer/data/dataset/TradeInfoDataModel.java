package com.example.digicu_customer.data.dataset;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TradeInfoDataModel implements Serializable {
    @SerializedName("id")
    private int tradeId;
    @SerializedName("coupon")
    private CouponDataModel couponDataModel;
    @SerializedName("owner")
    private String owner;
    @SerializedName("bound_value")
    private int boundValue;
    @SerializedName("createdDate")
    private Date createdDate;
    @SerializedName("proposals")
    @Expose
    private List<ProposalDataModel> proposals = null;

    @Override
    public String toString() {
        return "TradeDataModel{" +
                "tradeId=" + tradeId +
                ", couponDataModel=" + couponDataModel +
                ", owner='" + owner + '\'' +
                ", boundValue=" + boundValue +
                ", createdDate='" + createdDate + '\'' +
                ", proposals=" + proposals +
                '}';
    }

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

    public CouponDataModel getCouponDataModel() {
        return couponDataModel;
    }

    public void setCouponDataModel(CouponDataModel couponDataModel) {
        this.couponDataModel = couponDataModel;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getBoundValue() {
        return boundValue;
    }

    public void setBoundValue(Integer boundValue) {
        this.boundValue = boundValue;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<ProposalDataModel> getProposals() {
        return proposals;
    }

    public void setProposals(List<ProposalDataModel> proposals) {
        this.proposals = proposals;
    }
}
