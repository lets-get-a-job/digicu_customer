package com.example.digicu_customer.ui.main.trade.tabs.couponlist;

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

public class CouponListViewModel extends ViewModel {
    MutableLiveData<List<CouponDataModel>> couponTradeData;
    MutableLiveData<List<CouponDataModel>> couponTradeReqData;

    public MutableLiveData<List<CouponDataModel>> getTradingCouponModel() {
        if (couponTradeData == null) {
            couponTradeData = new MutableLiveData<>();
            loadTradeCouponData();
        }

        return couponTradeData;
    }

    protected void loadTradeCouponData() {
        DigicuCouponService digicuCouponService = ApiUtils.getDigicuCouponService();
        SocialUserDataModel socialUserDataModel = RetrofitClient.getSocialUserDataModel();

        digicuCouponService.getStateUserCouponData(socialUserDataModel.getPhone(), CouponDataModel.coupon_state.trading.toString())
                .enqueue(new Callback<List<CouponDataModel>>() {
                    @Override
                    public void onResponse(Call<List<CouponDataModel>> call, Response<List<CouponDataModel>> response) {
                        if (response.code() == 200) {
                            couponTradeData.setValue(response.body());
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

    public MutableLiveData<List<CouponDataModel>> getTradingReqCouponModel() {
        if (couponTradeReqData == null) {
            couponTradeReqData = new MutableLiveData<>();
            loadTradeReqCouponData();
        }

        return couponTradeReqData;
    }

    protected void loadTradeReqCouponData() {
        DigicuCouponService digicuCouponService = ApiUtils.getDigicuCouponService();
        SocialUserDataModel socialUserDataModel = RetrofitClient.getSocialUserDataModel();

        digicuCouponService.getStateUserCouponData(socialUserDataModel.getPhone(), CouponDataModel.coupon_state.trading_req.toString())
                .enqueue(new Callback<List<CouponDataModel>>() {
                    @Override
                    public void onResponse(Call<List<CouponDataModel>> call, Response<List<CouponDataModel>> response) {
                        if (response.code() == 200) {
                            couponTradeReqData.setValue(response.body());
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