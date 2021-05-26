package com.example.digicu_customer.ui.main.home.couponinfo.tradetable;

import androidx.lifecycle.ViewModel;

import com.example.digicu_customer.data.dataset.TradeInfoDataModel;

import java.util.List;

public class TradeTableViewModel extends ViewModel {
    private TradeInfoDataModel tradeInfoDataModel;

    public TradeInfoDataModel getTradeInfoDataModel() {
        return tradeInfoDataModel;
    }

    public void setTradeInfoDataModel(TradeInfoDataModel tradeInfoDataModel) {
        this.tradeInfoDataModel = tradeInfoDataModel;
    }
}