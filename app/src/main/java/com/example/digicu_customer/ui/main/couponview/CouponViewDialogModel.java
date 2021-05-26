package com.example.digicu_customer.ui.main.couponview;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.digicu_customer.data.dataset.CouponDataModel;
import com.example.digicu_customer.data.dataset.SocialUserDataModel;
import com.example.digicu_customer.data.remote.ApiUtils;
import com.example.digicu_customer.data.remote.DigicuCouponService;
import com.example.digicu_customer.data.remote.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.digicu_customer.general.GeneralVariable.TAG;

public class CouponViewDialogModel extends ViewModel {
    MutableLiveData<List<CouponDataModel>> availableCouponModel;

    public MutableLiveData<List<CouponDataModel>> getAvailableCouponModel() {
        if (availableCouponModel == null) {
            availableCouponModel = new MutableLiveData<>();
            loadAvailableCouponData();
        }

        return availableCouponModel;
    }

    protected void loadAvailableCouponData() {
        DigicuCouponService digicuCouponService = ApiUtils.getDigicuCouponService();
        SocialUserDataModel socialUserDataModel = RetrofitClient.getSocialUserDataModel();

        digicuCouponService.getStateUserCouponData(socialUserDataModel.getPhone(), CouponDataModel.coupon_state.done.toString())
            .enqueue(new Callback<List<CouponDataModel>>() {
                @Override
                public void onResponse(Call<List<CouponDataModel>> call, Response<List<CouponDataModel>> response) {
                    if (response.code() == 200) {
                        availableCouponModel.setValue(response.body());
                    } else {
                        Log.d(TAG, "onResponse err: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<CouponDataModel>> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                }
            });

    }
}