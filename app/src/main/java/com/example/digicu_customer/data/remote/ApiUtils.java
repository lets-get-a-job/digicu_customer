package com.example.digicu_customer.data.remote;

public class ApiUtils {
    // android emulator local server url
    public static final String LOCAL_BASE_URL = "http://10.0.2.2:3000";
    public static final String BASE_URL = "http://ec2-3-34-247-127.ap-northeast-2.compute.amazonaws.com:5555";

    public static DigicuUserService getDigicuUserService() {
        return RetrofitClient.getClient(BASE_URL).create(DigicuUserService.class);
//        return RetrofitClient.getClient(LOCAL_BASE_URL).create(DigicuUserService.class);
    }

    public static DigicuCouponService getDigicuCouponService() {
        return RetrofitClient.getClient(BASE_URL).create(DigicuCouponService.class);
    }
}
