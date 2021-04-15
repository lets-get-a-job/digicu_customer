package com.example.digicu_customer.data.remote;

import com.example.digicu_customer.data.dataset.ShopDataModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DigicuService {
    @GET("/users/{email}")
    Call<ShopDataModel> getCompanyInfo(@Path("email") String email);

    @GET("/users/companies")
    Call<ShopDataModel> getCompanies();
//
//    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
//    Call<SOAnswersResponse> getAnswers(@Query("tagged") String tags);
}
