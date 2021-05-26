package com.example.digicu_customer.ui.main.home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.digicu_customer.auth.DigicuAuth;
import com.example.digicu_customer.data.dataset.CouponDataModel;
import com.example.digicu_customer.data.dataset.ShopDataModel;
import com.example.digicu_customer.data.dataset.SocialUserDataModel;
import com.example.digicu_customer.data.remote.ApiUtils;
import com.example.digicu_customer.data.remote.DigicuCouponService;
import com.example.digicu_customer.data.remote.DigicuUserService;
import com.example.digicu_customer.data.remote.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.digicu_customer.general.GeneralVariable.TAG;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<List<CouponDataModel>> couponInfo;

    public MutableLiveData<List<CouponDataModel>> getCouponInfo() {
        if (couponInfo == null) {
            couponInfo = new MutableLiveData<>();
            loadCouponInfo();
        }

        return couponInfo;
    }

    protected void loadCouponInfo() {
        DigicuCouponService digicuCouponService = ApiUtils.getDigicuCouponService();
        SocialUserDataModel socialUserDataModel = RetrofitClient.getSocialUserDataModel();

        digicuCouponService.getStateUserCouponData(socialUserDataModel.getPhone(), CouponDataModel.coupon_state.normal.name()).enqueue(new Callback<List<CouponDataModel>>() {
            @Override
            public void onResponse(Call<List<CouponDataModel>> call, Response<List<CouponDataModel>> response) {
//                Log.d(TAG, "onResponse: " + response.body().toString());
                if (response.code() == 200) {
                    getCouponInfo().setValue(response.body());
                } else {
                    Log.d(TAG, "onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<CouponDataModel>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
