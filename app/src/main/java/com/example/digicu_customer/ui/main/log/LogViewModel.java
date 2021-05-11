package com.example.digicu_customer.ui.main.log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.digicu_customer.data.dataset.RecordOfPurchaseDataModel;
import com.example.digicu_customer.data.dataset.ShopDataModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogViewModel extends ViewModel {
    MutableLiveData<List<RecordOfPurchaseDataModel>> records;

    public MutableLiveData<List<RecordOfPurchaseDataModel>> getRecordsModel() {
        if (records == null) {
            records = new MutableLiveData<>();
            loadRecordData();
        }

        return records;
    }

    protected void loadRecordData() {
        List<RecordOfPurchaseDataModel> recordOfPurchaseDataModels = new ArrayList<>();

        recordOfPurchaseDataModels.add(new RecordOfPurchaseDataModel(1, new Date(), 3000, null,
                new ShopDataModel("2", "STARBUCKS", "010-1234-1234", "address", "owner", "naver.com", "", "test@test1.com")));
        recordOfPurchaseDataModels.add(new RecordOfPurchaseDataModel(2, new Date(), 3500, null,
                new ShopDataModel("2", "CAFE bene", "010-4434-1234", "address", "owner", "naver.com", "", "test@test2.com")));

        this.records.setValue(recordOfPurchaseDataModels);
    }
}