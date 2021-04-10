package com.example.digicu_customer.data.dataset;

import java.io.Serializable;

public class ShopDataModel implements Serializable {
    private String name;
    private String address;
    private String number;
    private String logo_url;

    public ShopDataModel(String name, String address, String number, String logo_url) {
        this.name = name;
        this.address = address;
        this.number = number;
        this.logo_url = logo_url;
    }

    @Override
    public String toString() {
        return "ShopDataModel{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", number='" + number + '\'' +
                ", logo_url='" + logo_url + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }
}
