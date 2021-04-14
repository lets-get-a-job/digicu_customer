package com.example.digicu_customer.data.remote;

public class ApiUtils {
    public static final String BASE_URL = "http://localhost:3000";

    public static DigicuService getSOSoService() {
        return RetrofitClient.getClient(BASE_URL).create(DigicuService.class);
    }
}
