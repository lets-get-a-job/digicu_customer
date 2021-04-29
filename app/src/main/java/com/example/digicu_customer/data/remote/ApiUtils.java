package com.example.digicu_customer.data.remote;

public class ApiUtils {
    // android emulator local server url
    public static final String USER_BASE_URL = "https://virtserver.swaggerhub.com/lets-get-a-job/user-service/1.0.0";
    public static final String COUPON_BASE_URL = "https://virtserver.swaggerhub.com/digicu/coupon_service/1.0.0";

    public static DigicuService getDigicuUserService() {
        return RetrofitClient.getClient(USER_BASE_URL).create(DigicuService.class);
    }

    public static DigicuService getDigicuCouponService() {
        return RetrofitClient.getClient(COUPON_BASE_URL).create(DigicuService.class);
    }
}
