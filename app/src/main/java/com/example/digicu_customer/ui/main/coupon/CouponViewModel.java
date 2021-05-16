package com.example.digicu_customer.ui.main.coupon;

import android.os.Build;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.digicu_customer.data.dataset.CouponDataModel;
import com.example.digicu_customer.data.dataset.SocialUserDataModel;
import com.example.digicu_customer.data.remote.ApiUtils;
import com.example.digicu_customer.data.remote.DigicuCouponService;
import com.example.digicu_customer.data.remote.RetrofitClient;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.digicu_customer.general.GeneralVariable.TAG;

public class CouponViewModel extends ViewModel {
    MutableLiveData<List<CouponDataModel>> availableCouponModel;
    MutableLiveData<List<CouponDataModel>> unavailableCouponModel;

    public MutableLiveData<List<CouponDataModel>> getAvailableCouponModel() {
        if (availableCouponModel == null) {
            availableCouponModel = new MutableLiveData<>();
            availableCouponModel.setValue(new ArrayList<>());
            loadAvailableCouponData();
        }

        return availableCouponModel;
    }

    protected void loadAvailableCouponData() {
        List<CouponDataModel> list = availableCouponModel.getValue();
        list.clear();

        DigicuCouponService digicuCouponService = ApiUtils.getDigicuCouponService();
        SocialUserDataModel socialUserDataModel = RetrofitClient.getSocialUserDataModel();

        digicuCouponService.getStateUserCouponData(socialUserDataModel.getPhone(), CouponDataModel.coupon_state.done.toString())
            .enqueue(new Callback<List<CouponDataModel>>() {
                @Override
                public void onResponse(Call<List<CouponDataModel>> call, Response<List<CouponDataModel>> response) {
                    if (response.code() == 200) {
                        list.addAll(response.body());
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            list.sort(new Comparator<CouponDataModel>() {
                                @Override
                                public int compare(CouponDataModel couponDataModel, CouponDataModel t1) {
                                    return couponDataModel.getExpirationDate().compareTo(t1.getExpirationDate());
                                }
                            });
                        }
                        availableCouponModel.setValue(list);
                    } else {
                        Log.d(TAG, "onResponse err: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<CouponDataModel>> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                }
            });

        digicuCouponService.getStateUserCouponData(socialUserDataModel.getPhone(), CouponDataModel.coupon_state.trading.toString())
                .enqueue(new Callback<List<CouponDataModel>>() {
                    @Override
                    public void onResponse(Call<List<CouponDataModel>> call, Response<List<CouponDataModel>> response) {
                        if (response.code() == 200) {
                            list.addAll(response.body());
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                list.sort(new Comparator<CouponDataModel>() {
                                    @Override
                                    public int compare(CouponDataModel couponDataModel, CouponDataModel t1) {
                                        return couponDataModel.getExpirationDate().compareTo(t1.getExpirationDate());
                                    }
                                });
                            }
                            availableCouponModel.setValue(list);
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

    public MutableLiveData<List<CouponDataModel>> getUnavailableCouponModel() {
        if (unavailableCouponModel == null) {
            unavailableCouponModel = new MutableLiveData<>();
            loadUnavailableCouponData();
        }

        return unavailableCouponModel;
    }

    protected void loadUnavailableCouponData() {
        DigicuCouponService digicuCouponService = ApiUtils.getDigicuCouponService();
        SocialUserDataModel socialUserDataModel = RetrofitClient.getSocialUserDataModel();

        digicuCouponService.getStateUserCouponData(socialUserDataModel.getPhone(), CouponDataModel.coupon_state.used.toString())
                .enqueue(new Callback<List<CouponDataModel>>() {
                    @Override
                    public void onResponse(Call<List<CouponDataModel>> call, Response<List<CouponDataModel>> response) {
                        if (response.code() == 200) {
                            unavailableCouponModel.setValue(response.body());
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