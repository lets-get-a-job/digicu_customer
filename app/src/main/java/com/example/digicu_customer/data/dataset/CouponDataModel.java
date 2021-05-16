package com.example.digicu_customer.data.dataset;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import static com.example.digicu_customer.general.GeneralVariable.TAG;

public class CouponDataModel implements Serializable {
    public enum coupon_state {
        done, // available
        used,
        normal,
        expired,
        trading
    }

    // 쿠폰 일련번호
    @SerializedName("id")
    private int couponKey;
    @SerializedName("name")
    private String name;
    @SerializedName("owner")
    private String owner;
    @SerializedName("type")
    private CouponInfoDataModel.CouponType type;
    @SerializedName("value")
    private int value;
    @SerializedName("state")
    private String state;
    @SerializedName("count")
    private int count;
    @SerializedName("goal")
    private int goal;
    @SerializedName("expirationDate")
    private Date expirationDate;
    @SerializedName("createdDate")
    private Date createdAt;

    public CouponDataModel(int couponKey, String name, String owner, CouponInfoDataModel.CouponType type, int value, String state, int count, int goal, Date expirationDate, Date createdAt) {
        this.couponKey = couponKey;
        this.name = name;
        this.owner = owner;
        this.type = type;
        this.value = value;
        this.state = state;
        this.count = count;
        this.goal = goal;
        this.expirationDate = expirationDate;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "CouponDataModel{" +
                "couponKey=" + couponKey +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", type=" + type +
                ", value=" + value +
                ", state=" + state +
                ", count=" + count +
                ", goal=" + goal +
                ", expirationDate=" + expirationDate +
                ", createdAt=" + createdAt +
                '}';
    }

    public int getCouponKey() {
        return couponKey;
    }

    public void setCouponKey(int couponKey) {
        this.couponKey = couponKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public CouponInfoDataModel.CouponType getType() {
        return type;
    }

    public void setType(String type) {
        if (type.equals("DISCOUNT")) {
            this.type = CouponInfoDataModel.CouponType.DISCOUNT;
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
