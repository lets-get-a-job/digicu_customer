package com.example.digicu_customer.data.dataset;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class DigicuTokenDataModel implements Serializable {
    @SerializedName("digicu_token")
    private String token;
    @SerializedName("expires_in")
    private String expire;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }
}
