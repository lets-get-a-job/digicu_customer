package com.example.digicu_customer.data.remote;

import com.example.digicu_customer.data.dataset.CouponDataModel;
import com.example.digicu_customer.data.dataset.CouponInfoDataModel;
import com.example.digicu_customer.data.dataset.CouponTradeRegisterDataModel;
import com.example.digicu_customer.data.dataset.ProposalDataModel;
import com.example.digicu_customer.data.dataset.ProposalRequestDataModel;
import com.example.digicu_customer.data.dataset.TradeDataModel;
import com.example.digicu_customer.data.dataset.TradeInfoDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DigicuCouponService {
    @GET("/coupon/coupon_spec")
    Call<List<CouponInfoDataModel>> getCouponInfoByOwnerEmail(@Query("email") String email);

    @GET("/coupon")
    Call<List<CouponDataModel>> getAllUserCouponDataWithState(@Query("state") String state);

    @GET("/coupon/{id}")
    Call<CouponDataModel> getCouponDataByID(@Path("id") int id);

    @GET("/coupon")
    Call<List<CouponDataModel>> getStateUserCouponData(@Query("phone") String phone, @Query("state") String state);

    @GET("/coupon/trade/{id}")
    Call<TradeInfoDataModel> getCouponTradeInfo(@Path("id") Integer id);

    @POST("/coupon/trade")
    Call<TradeInfoDataModel> postCouponTrade(@Body CouponTradeRegisterDataModel couponTradeRegisterDataModel);

    @DELETE("/coupon/trade/{id}")
    Call<String> deleteCouponTrade(@Path("id") int id);

    @GET("/coupon/proposal")
    Call<List<ProposalDataModel>> getProposalRequestWithCouponId(@Query("coupon_id") int id);

    @POST("/coupon/proposal")
    Call<ProposalDataModel> postProposalRequest(@Body ProposalRequestDataModel proposalRequestDataModel);

    @DELETE("/coupon/proposal/{id}")
    Call<String> deleteProposalRequest(@Path("id") int id);

    @POST("/coupon/proposal/{id}/accept")
    Call<String> postProposalAccept(@Path("id") int id);
}
