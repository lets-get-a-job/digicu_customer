package com.example.digicu_customer.data.remote;

public class ApiUtils {
    // android emulator local server url
    public static final String BASE_URL = "http://10.0.2.2:3000";

    public static DigicuService getDigicuService() {
        return RetrofitClient.getClient(BASE_URL).create(DigicuService.class);
    }
}
