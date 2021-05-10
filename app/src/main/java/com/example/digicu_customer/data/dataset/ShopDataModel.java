package com.example.digicu_customer.data.dataset;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ShopDataModel implements Serializable {
    @SerializedName("company_number")
    private String id;
    @SerializedName("company_name")
    private String name;
    @SerializedName("company_phone")
    private String phone;
    @SerializedName("company_address")
    private String address;
    @SerializedName("company_owner")
    private String owner;
    @SerializedName("company_homepage")
    private String company_homepage;
    @SerializedName("company_logo")
    private String logo_url;
    @SerializedName("email")
    private String email;

    public ShopDataModel(String id, String name, String phone, String address, String owner, String company_homepage, String logo_url, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.owner = owner;
        this.company_homepage = company_homepage;
        this.logo_url = logo_url;
        this.email = email;
    }

    @Override
    public String toString() {
        return "ShopDataModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", owner='" + owner + '\'' +
                ", company_homepage='" + company_homepage + '\'' +
                ", logo_url='" + logo_url + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCompany_homepage() {
        return company_homepage;
    }

    public void setCompany_homepage(String company_homepage) {
        this.company_homepage = company_homepage;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
