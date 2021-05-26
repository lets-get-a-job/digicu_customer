package com.example.digicu_customer.general;

import java.text.SimpleDateFormat;

public class CustomDate {
    public static SimpleDateFormat getDigicuDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    public static SimpleDateFormat getDigicuDateFormatDetail() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
