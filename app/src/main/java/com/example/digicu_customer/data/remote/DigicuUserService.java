package com.example.digicu_customer.data.remote;

import com.example.digicu_customer.data.dataset.DigicuTokenDataModel;
import com.example.digicu_customer.data.dataset.ShopDataModel;
import com.example.digicu_customer.data.dataset.SocialUserDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DigicuUserService {
//    @GET("/users/{email}")
//    Call<ShopDataModel> getCompanyInfo(@Path("email") String email);

    @GET("/users/company")
    Call<List<ShopDataModel>> getCompanies(@Query("count") int count, @Query("page") int page, @Query("orderby") String orderby, @Query("desc") boolean desc);

    @GET("/users/company")
    Call<List<ShopDataModel>> getSearchCompanies(@Query("include") String name, @Query("count") int count, @Query("page") int page, @Query("orderby") String orderBy, @Query("desc") boolean desc);

    @GET("/users/social/{social_id}")
    Call<SocialUserDataModel> getSocial(@Path("social_id") String social_id);

    @POST("/users/social")
    Call<DigicuTokenDataModel> postSocial(@Body SocialUserDataModel socialUserDataModel);

    @PATCH("/users/social")
    Call<DigicuTokenDataModel> patchSocial(@Body SocialUserDataModel socialUserDataModel);
}
