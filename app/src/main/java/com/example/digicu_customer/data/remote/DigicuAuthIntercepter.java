package com.example.digicu_customer.data.remote;

import android.util.Log;

import com.example.digicu_customer.general.GeneralVariable;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class DigicuAuthIntercepter implements Interceptor {
    private String token;

    public DigicuAuthIntercepter(String token) {
        this.token = token;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request originRequest = chain.request();

        Request newRequest = originRequest.newBuilder()
                .header("Authorization", "Bearer " + token)
                .build();

        Log.d(GeneralVariable.TAG, "intercept: " + newRequest.headers().get("Authorization"));

        return chain.proceed(newRequest);
    }
}
