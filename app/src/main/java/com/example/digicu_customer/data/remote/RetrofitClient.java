package com.example.digicu_customer.data.remote;

import android.util.Log;

import com.example.digicu_customer.auth.DigicuAuth;
import com.example.digicu_customer.data.dataset.SocialUserDataModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.digicu_customer.general.GeneralVariable.TAG;

public class RetrofitClient {
    private static Retrofit retrofit = null;
    private static String prevBaseUrl = "";
    private static SocialUserDataModel socialUserDataModel = null;

    public static Retrofit createClient(String baseUrl) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .setLenient()
                .create();
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//        } else {
//            gson = new GsonBuilder()
//                    .setLenient()
//                    .create();
//        }

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.addInterceptor(httpLoggingInterceptor);
//        client.networkInterceptors().add(new DigicuOKHttpIntercepter());

        Log.d(TAG, "getClient: " + socialUserDataModel);

        if (socialUserDataModel != null) {
            String token = DigicuAuth.getToken(socialUserDataModel);
            Log.d(TAG, "getClient token: " + token);
            DigicuAuthIntercepter digicuAuthIntercepter = new DigicuAuthIntercepter(token);

            builder.addInterceptor(digicuAuthIntercepter);
        }

        OkHttpClient client = builder.build();


        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        return retrofit;
    }

    public static Retrofit getClient(String baseUrl) {
        prevBaseUrl = baseUrl;

        if (retrofit == null) createClient(baseUrl);

        return retrofit;
    }

    public static SocialUserDataModel getSocialUserDataModel() {
        return socialUserDataModel;
    }

    public static void setSocialUserDataModel(SocialUserDataModel socialUserDataModel) {
        RetrofitClient.socialUserDataModel = socialUserDataModel;
        if (prevBaseUrl != null || !prevBaseUrl.isEmpty()) {
            createClient(prevBaseUrl);
        }
    }
}
