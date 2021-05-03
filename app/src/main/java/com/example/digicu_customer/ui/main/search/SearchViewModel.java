package com.example.digicu_customer.ui.main.search;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.digicu_customer.data.dataset.ShopDataModel;
import com.example.digicu_customer.data.remote.ApiUtils;
import com.example.digicu_customer.data.remote.DigicuUserService;
import com.example.digicu_customer.general.GeneralVariable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.digicu_customer.general.GeneralVariable.TAG;

public class SearchViewModel extends ViewModel {
    MutableLiveData<List<ShopDataModel>> shopDataModelMutableLiveData;
    int curPage = 1;
    int loadCount = 30;

    public MutableLiveData<List<ShopDataModel>> getShopDataModelMutableLiveData() {
        if (shopDataModelMutableLiveData == null) {
            shopDataModelMutableLiveData = new MutableLiveData<>();
            loadShopDataModel();
        }

        return shopDataModelMutableLiveData;
    }

    private void loadShopDataModel() {
        DigicuUserService digicuUserService = ApiUtils.getDigicuUserService();

        digicuUserService.getCompanies(loadCount, curPage, "company_name", false).enqueue(new Callback<List<ShopDataModel>>() {
            @Override
            public void onResponse(Call<List<ShopDataModel>> call, Response<List<ShopDataModel>> response) {
                if (response.body() == null) return;

                List<ShopDataModel> shopDataModels = shopDataModelMutableLiveData.getValue();
                if (shopDataModels == null) {
                    shopDataModels = new ArrayList<>();
                }

                shopDataModels.addAll(response.body());

//                for (ShopDataModel shopDataModel : response.body()) {
//                    Log.d(TAG, "onResponse: " + shopDataModel.toString());
//                    shopDataModels.add(shopDataModel);
//                }

                // for signaling to observer
                shopDataModelMutableLiveData.setValue(shopDataModels);
            }

            @Override
            public void onFailure(Call<List<ShopDataModel>> call, Throwable t) {
                Log.d(TAG, "onFailure header: " + t.toString());
            }
        });
    }

    protected void loadShopDataModel(String company) {
        DigicuUserService digicuUserService = ApiUtils.getDigicuUserService();

        digicuUserService.getSearchCompanies(company ,loadCount, curPage, "company_name", false).enqueue(new Callback<List<ShopDataModel>>() {
            @Override
            public void onResponse(Call<List<ShopDataModel>> call, Response<List<ShopDataModel>> response) {
                if (response.body() == null) return;

                List<ShopDataModel> shopDataModels = shopDataModelMutableLiveData.getValue();
                if (shopDataModels == null) {
                    shopDataModels = new ArrayList<>();
                } else {
                    shopDataModels.clear();
                }

                shopDataModels.addAll(response.body());

//                for (ShopDataModel shopDataModel : response.body()) {
//                    Log.d(TAG, "onResponse: " + shopDataModel.toString());
//                    shopDataModels.add(shopDataModel);
//                }

                // for signaling to observer
                shopDataModelMutableLiveData.setValue(shopDataModels);
            }

            @Override
            public void onFailure(Call<List<ShopDataModel>> call, Throwable t) {
                Log.d(TAG, "onFailure header: " + t.toString());
            }
        });
    }

}
