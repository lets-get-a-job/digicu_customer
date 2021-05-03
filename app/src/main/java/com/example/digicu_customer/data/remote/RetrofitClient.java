package com.example.digicu_customer.data.remote;

import com.example.digicu_customer.auth.DigicuAuth;
import com.example.digicu_customer.data.dataset.SocialUserDataModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;
    private static SocialUserDataModel socialUserDataModel = null;

    public static Retrofit getClient(String baseUrl) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.addInterceptor(httpLoggingInterceptor);
//        client.networkInterceptors().add(new DigicuOKHttpIntercepter());

        if (socialUserDataModel != null) {
            String token = DigicuAuth.getToken(socialUserDataModel);

            DigicuAuthIntercepter digicuAuthIntercepter = new DigicuAuthIntercepter(token);

            builder.addInterceptor(digicuAuthIntercepter);
        }

        OkHttpClient client = builder.build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        }

        return retrofit;
    }

    public static void setSocialUserDataModel(SocialUserDataModel socialUserDataModel) {
        RetrofitClient.socialUserDataModel = socialUserDataModel;
    }
}
