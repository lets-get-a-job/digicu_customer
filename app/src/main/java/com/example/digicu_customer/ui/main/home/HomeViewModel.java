package com.example.digicu_customer.ui.main.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.digicu_customer.data.dataset.CouponInfoDataModel;
import com.example.digicu_customer.data.dataset.ShopDataModel;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<List<CouponInfoDataModel>> couponInfo;

    public MutableLiveData<List<CouponInfoDataModel>> getCouponInfo() {
        if (couponInfo == null) {
            couponInfo = new MutableLiveData<>();
            loadCouponInfo();
        }

        return couponInfo;
    }

    protected void loadCouponInfo() {
        // get data from server
        ShopDataModel shopDataModels[] = new ShopDataModel[5];
        shopDataModels[0] = new ShopDataModel("test1", "상도동1", "010-1234-1234", "");
        shopDataModels[1] = new ShopDataModel("test2", "상도동2", "010-1234-1234", "");
        shopDataModels[2] = new ShopDataModel("test3", "상도동3", "010-1234-1234", "");
        shopDataModels[3] = new ShopDataModel("test4", "상도동4", "010-1234-1234", "");
        shopDataModels[4] = new ShopDataModel("test5", "상도동5", "010-1234-1234", "");

        List<CouponInfoDataModel> couponInfoDataModelList = new ArrayList<>();
        couponInfoDataModelList.add(new CouponInfoDataModel(shopDataModels[0], CouponInfoDataModel.CouponType.STAMP, 10));
        couponInfoDataModelList.add(new CouponInfoDataModel(shopDataModels[1], CouponInfoDataModel.CouponType.MILEAGE, 0));
        couponInfoDataModelList.add(new CouponInfoDataModel(shopDataModels[2], CouponInfoDataModel.CouponType.STAMP, 15));
        couponInfoDataModelList.add(new CouponInfoDataModel(shopDataModels[3], CouponInfoDataModel.CouponType.STAMP, 10));
        couponInfoDataModelList.add(new CouponInfoDataModel(shopDataModels[4], CouponInfoDataModel.CouponType.MILEAGE, 0));

        this.couponInfo.setValue(couponInfoDataModelList);
    }
}
