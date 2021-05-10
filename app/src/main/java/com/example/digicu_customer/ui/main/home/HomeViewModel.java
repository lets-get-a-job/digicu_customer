package com.example.digicu_customer.ui.main.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.digicu_customer.data.dataset.ShopDataModel;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<List<ShopDataModel>> shopInfo;

    public MutableLiveData<List<ShopDataModel>> getShopInfo() {
        if (shopInfo == null) {
            shopInfo = new MutableLiveData<>();
            loadShopInfo();
        }

        return shopInfo;
    }

    protected void loadShopInfo() {
        // get data from server
//        ShopDataModel shopDataModels[] = new ShopDataModel[5];
//        shopDataModels[0] = new ShopDataModel("1233", "test1", "010-1234-1234", "상도동1", "JJS", "https://naver.com/", "", "");
//        shopDataModels[1] = new ShopDataModel("1234", "test2", "010-1234-1234", "상도동1", "JJS", "https://naver.com/", "", "");
//        shopDataModels[2] = new ShopDataModel("1235", "test3", "010-1234-1234", "상도동1", "JJS", "https://naver.com/", "", "");
//        shopDataModels[3] = new ShopDataModel("1236", "test4", "010-1234-1234", "상도동1", "JJS", "https://naver.com/", "", "");
//        shopDataModels[4] = new ShopDataModel("1237", "test5", "010-1234-1234", "상도동1", "JJS", "https://naver.com/", "", "");
//
//        List<CouponInfoDataModel> couponInfoDataModelList = new ArrayList<>();
//        couponInfoDataModelList.add(new CouponInfoDataModel(shopDataModels[0], CouponInfoDataModel.CouponType.STAMP, 10));
//        couponInfoDataModelList.add(new CouponInfoDataModel(shopDataModels[1], CouponInfoDataModel.CouponType.STAMP, 10));
//        couponInfoDataModelList.add(new CouponInfoDataModel(shopDataModels[2], CouponInfoDataModel.CouponType.STAMP, 15));
//        couponInfoDataModelList.add(new CouponInfoDataModel(shopDataModels[3], CouponInfoDataModel.CouponType.STAMP, 10));
//        couponInfoDataModelList.add(new CouponInfoDataModel(shopDataModels[4], CouponInfoDataModel.CouponType.STAMP, 10));

        this.shopInfo.setValue(new ArrayList<>());
    }
}
