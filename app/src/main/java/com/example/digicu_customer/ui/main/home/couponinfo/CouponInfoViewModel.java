package com.example.digicu_customer.ui.main.home.couponinfo;

import android.util.Log;

import androidx.annotation.LongDef;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.digicu_customer.data.dataset.CouponDataModel;
import com.example.digicu_customer.data.dataset.CouponInfoDataModel;
import com.example.digicu_customer.data.dataset.ProductDataModel;
import com.example.digicu_customer.data.dataset.RecordOfPurchaseDataModel;
import com.example.digicu_customer.general.GeneralVariable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CouponInfoViewModel extends ViewModel {
    private MutableLiveData<List<RecordOfPurchaseDataModel>> recordOfPurchaseData;
    private MutableLiveData<List<CouponDataModel>> couponData;
    private MutableLiveData<CouponInfoDataModel> couponInfoDataModel;

    public MutableLiveData<CouponInfoDataModel> getCouponInfoDataModel() {
        if (couponInfoDataModel == null) {
            this.couponInfoDataModel = new MutableLiveData<>();

            if(couponInfoDataModel.getValue() != null) {
                loadCouponInfoData();
            }
        }

        return couponInfoDataModel;
    }

    public void loadCouponInfoData() {
        loadCouponData();
        loadRecordData();
    }

    private void loadRecordData() {
        if (recordOfPurchaseData == null) {
            recordOfPurchaseData = new MutableLiveData<>();
        }

        // temporary data
        List<ProductDataModel> products1 = new ArrayList<>();
        products1.add(new ProductDataModel("A", 1000, 3));
        products1.add(new ProductDataModel("B", 2000, 1));
        List<ProductDataModel> products2 = new ArrayList<>();
        products2.add(new ProductDataModel("C", 1000, 3));
        products2.add(new ProductDataModel("D", 2000, 1));

        List<RecordOfPurchaseDataModel> recordOfPurchaseDataModels = new ArrayList<>();
        recordOfPurchaseDataModels.add(new RecordOfPurchaseDataModel(couponInfoDataModel.getValue(), 1, new Date(), products1));
        recordOfPurchaseDataModels.add(new RecordOfPurchaseDataModel(couponInfoDataModel.getValue(), 2, new Date(), products2));
        recordOfPurchaseDataModels.add(new RecordOfPurchaseDataModel(couponInfoDataModel.getValue(), 3, new Date(), products1));
        recordOfPurchaseDataModels.add(new RecordOfPurchaseDataModel(couponInfoDataModel.getValue(), 4, new Date(), products2));
        recordOfPurchaseDataModels.add(new RecordOfPurchaseDataModel(couponInfoDataModel.getValue(), 5, new Date(), products1));
        //----------------

        recordOfPurchaseData.setValue(recordOfPurchaseDataModels);
    }

    private void loadCouponData() {
        if (couponData == null) {
            couponData = new MutableLiveData<>();
        }

        Log.d(GeneralVariable.TAG, "loadCouponData: " + couponInfoDataModel.getValue().toString());

        List<CouponDataModel> couponDataModels = new ArrayList<>();
        couponDataModels.add(new CouponDataModel(couponInfoDataModel.getValue().getShopDataModel(), 1,"무적권올해취업 7000원 할인 쿠폰", new Date(), true));
        couponDataModels.add(new CouponDataModel(couponInfoDataModel.getValue().getShopDataModel(), 1,"무적권올해취업 3000원 할인 쿠폰", new Date(), true));
        couponDataModels.add(new CouponDataModel(couponInfoDataModel.getValue().getShopDataModel(), 1,"무적권올해취업 5000원 할인 쿠폰", new Date(), true));
        couponDataModels.add(new CouponDataModel(couponInfoDataModel.getValue().getShopDataModel(), 1,"무적권올해취업 5000원 할인 쿠폰", new Date(), true));
        couponDataModels.add(new CouponDataModel(couponInfoDataModel.getValue().getShopDataModel(), 1,"무적권올해취업 5000원 할인 쿠폰", new Date(), true));

        couponData.setValue(couponDataModels);
    }

    public MutableLiveData<List<RecordOfPurchaseDataModel>> getRecordOfPurchaseData() {
        if (recordOfPurchaseData == null) {
            recordOfPurchaseData = new MutableLiveData<>();
        }

        return recordOfPurchaseData;
    }

    public MutableLiveData<List<CouponDataModel>> getCouponData() {
        if (couponData == null) {
            couponData = new MutableLiveData<>();
        }

        return couponData;
    }
}
