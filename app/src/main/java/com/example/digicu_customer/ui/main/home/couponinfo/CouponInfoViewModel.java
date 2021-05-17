package com.example.digicu_customer.ui.main.home.couponinfo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.digicu_customer.data.dataset.CouponInfoDataModel;
import com.example.digicu_customer.data.dataset.ShopDataModel;
import com.example.digicu_customer.data.remote.ApiUtils;
import com.example.digicu_customer.data.remote.DigicuCouponService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.digicu_customer.general.GeneralVariable.TAG;

public class CouponInfoViewModel extends ViewModel {
    private MutableLiveData<List<CouponInfoDataModel>> couponInfoDataModel;
    private MutableLiveData<ShopDataModel> shopDataModel;

    public CouponInfoViewModel(ShopDataModel shopDataModel) {
        this.shopDataModel = new MutableLiveData<>();
        this.shopDataModel.setValue(shopDataModel);
    }

    public MutableLiveData<List<CouponInfoDataModel>> getCouponInfoDataModel() {
        if (couponInfoDataModel == null) {
            this.couponInfoDataModel = new MutableLiveData<>();
        }

        loadCouponData();

        return couponInfoDataModel;
    }

    protected void loadCouponData() {
//        Log.d(TAG, "loadCouponData: " + couponInfoDataModel.getValue().toString());
        ShopDataModel shopDataModel = this.shopDataModel.getValue();
        if(shopDataModel == null) return;

        Log.d(TAG, "loadCouponData: " + shopDataModel.toString());
        DigicuCouponService digicuCouponService = ApiUtils.getDigicuCouponService();
        digicuCouponService.getCouponInfoByOwnerEmail(shopDataModel.getEmail()).enqueue(new Callback<List<CouponInfoDataModel>>() { // List<CouponInfoDataModel>
            @Override
            public void onResponse(Call<List<CouponInfoDataModel>> call, Response<List<CouponInfoDataModel>> response) {
                Log.d(TAG, "CouponInfoViewModel onResponse: " + response.code());
                if (response.code() == 200) {
//                    Log.d(TAG, "onResponse: " + response.body().get(0));
                    couponInfoDataModel.setValue(response.body());
                } else {
                    Log.d(TAG, "CouponInfoViewModel onResponse: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<CouponInfoDataModel>> call, Throwable t) {
                Log.d(TAG, "CouponInfoViewModel onFailure: " + t.getMessage());
            }
        });
    }
}
