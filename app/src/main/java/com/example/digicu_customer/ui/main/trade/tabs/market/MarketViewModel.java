package com.example.digicu_customer.ui.main.trade.tabs.market;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.digicu_customer.data.dataset.CouponDataModel;
import com.example.digicu_customer.data.dataset.SocialUserDataModel;
import com.example.digicu_customer.data.remote.ApiUtils;
import com.example.digicu_customer.data.remote.DigicuCouponService;
import com.example.digicu_customer.data.remote.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.digicu_customer.general.GeneralVariable.TAG;

public class MarketViewModel extends ViewModel {
    MutableLiveData<List<CouponDataModel>> couponMutableLiveData;

    public MutableLiveData<List<CouponDataModel>> getCouponModel() {
        if (couponMutableLiveData == null) {
            couponMutableLiveData = new MutableLiveData<>();
            loadCouponData();
        }

        return couponMutableLiveData;
    }

    protected void loadCouponData() {
        DigicuCouponService digicuCouponService = ApiUtils.getDigicuCouponService();

        digicuCouponService.getAllUserCouponDataWithState(CouponDataModel.coupon_state.trading.toString())
                .enqueue(new Callback<List<CouponDataModel>>() {
                    @Override
                    public void onResponse(Call<List<CouponDataModel>> call, Response<List<CouponDataModel>> response) {
                        if (response.code() == 200) {
                            couponMutableLiveData.setValue(response.body());
                            Log.d(TAG, "onResponse: " + response.body());
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