package com.example.digicu_customer.data.dataset;

import com.example.digicu_customer.util.qr_generator.CustomDate;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SocialUserDataModel implements Serializable {
    @SerializedName("social_id")
    private String social_id;
    @SerializedName("token")
    private String token;
    @SerializedName("email")
    private String email;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("profile_image")
    private String profile_image;
    @SerializedName("thumbnail_image")
    private String thumbnail_image;
    @SerializedName("registration_date")
    private String registration_date;
    @SerializedName("letter_ok")
    private String letter_ok;
    @SerializedName("phone")
    private String phone;
    @SerializedName("fcm_token")
    private String fcm_token;

    private DigicuTokenDataModel digicuTokenDataModel;

    public SocialUserDataModel(String social_id, String token, String email, String nickname, String profile_image, String thumbnail_image, Date registration_date, Date letter_ok, String phone) {
        this.social_id = social_id;
        this.token = token;
        this.email = email;
        this.nickname = nickname;
        this.profile_image = profile_image;
        this.thumbnail_image = thumbnail_image;

        SimpleDateFormat dateFormat = CustomDate.getDigicuDataFormat();

        this.registration_date = dateFormat.format(registration_date);
        this.letter_ok = dateFormat.format(letter_ok);

        this.phone = phone;
    }

    @Override
    public String toString() {
        return "SocialUserDataModel{" +
                "social_id='" + social_id + '\'' +
                ", token='" + token + '\'' +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", profile_image='" + profile_image + '\'' +
                ", thumbnail_image='" + thumbnail_image + '\'' +
                ", registration_date='" + registration_date + '\'' +
                ", letter_ok='" + letter_ok + '\'' +
                ", phone='" + phone + '\'' +
                ", fcm_token='" + fcm_token + '\'' +
                ", digicuTokenDataModel=" + digicuTokenDataModel +
                '}';
    }

    public String getSocial_id() {
        return social_id;
    }

    public void setSocial_id(String social_id) {
        this.social_id = social_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getThumbnail_image() {
        return thumbnail_image;
    }

    public void setThumbnail_image(String thumbnail_image) {
        this.thumbnail_image = thumbnail_image;
    }

    public Date getRegistration_date() {
        return new Date(registration_date);
    }

    public void setRegistration_date(Date registration_date) {
        this.registration_date = CustomDate.getDigicuDataFormat().format(registration_date);
    }

    public Date getLetter_ok() {
        return new Date(letter_ok);
    }

    public void setLetter_ok(Date letter_ok) {
        this.letter_ok = CustomDate.getDigicuDataFormat().format(letter_ok);
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }

    public void setLetter_ok(String letter_ok) {
        this.letter_ok = letter_ok;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public DigicuTokenDataModel getDigicuTokenDataModel() {
        return digicuTokenDataModel;
    }

    public void setDigicuTokenDataModel(DigicuTokenDataModel digicuTokenDataModel) {
        this.digicuTokenDataModel = digicuTokenDataModel;
    }

    public String getFcm_token() {
        return fcm_token;
    }

    public void setFcm_token(String fcm_token) {
        this.fcm_token = fcm_token;
    }
}
