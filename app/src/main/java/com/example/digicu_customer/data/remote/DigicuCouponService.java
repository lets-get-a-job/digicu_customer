package com.example.digicu_customer.data.remote;

import com.example.digicu_customer.data.dataset.CouponDataModel;
import com.example.digicu_customer.data.dataset.CouponInfoDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DigicuCouponService {
    @GET("/coupon/coupon_spec")
    Call<List<CouponInfoDataModel>> getCouponInfoByOwnerEmail(@Query("email") String email);

    @GET("/coupon/")
    Call<List<CouponDataModel>> getAllUserCouponDataWithState(@Query("state") String state);

    @GET("/coupon/{id}")
    Call<CouponDataModel> getCouponDataByID(@Path("id") int id);

    @GET("/coupon/")
    Call<List<CouponDataModel>> getStateUserCouponData(@Query("phone") String phone, @Query("state") String state);
}
