package com.example.digicu_customer.ui.main.home.savingcoupon.receipt;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.digicu_customer.data.dataset.RecordOfPurchaseDataModel;

public class ReceiptViewModel extends ViewModel {
    MutableLiveData<RecordOfPurchaseDataModel> record;

    public MutableLiveData<RecordOfPurchaseDataModel> getRecord() {
        if (record == null) {
            record = new MutableLiveData<>();
            loadRecordOfPurchaseData();
        }

        return record;
    }

    private void loadRecordOfPurchaseData() {
        // get data from server

    }

    // sample
    protected void loadRecordOfPurchaseData(RecordOfPurchaseDataModel recordOfPurchaseDataModel) {
        // get data from server
        record.setValue(recordOfPurchaseDataModel);
    }
}
