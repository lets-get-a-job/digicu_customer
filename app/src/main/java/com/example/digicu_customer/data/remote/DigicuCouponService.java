package com.example.digicu_customer.data.remote;

import com.example.digicu_customer.data.dataset.ShopDataModel;
import com.example.digicu_customer.data.dataset.SocialUserDataModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DigicuCouponService {
    @GET("/coupon/hi")
    Call<String> getTest();
}
