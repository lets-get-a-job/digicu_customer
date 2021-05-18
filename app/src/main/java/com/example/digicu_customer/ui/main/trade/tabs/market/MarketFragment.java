package com.example.digicu_customer.ui.main.trade.tabs.market;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.CouponDataModel;

import java.util.List;

public class MarketFragment extends Fragment {
    private RecyclerView recyclerView;
    private MarketListAdapter marketListAdapter;
    private MarketViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_market, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.trade_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        marketListAdapter = new MarketListAdapter();
        recyclerView.setAdapter(marketListAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MarketViewModel.class);

        final Observer<List<CouponDataModel>> observer = new Observer<List<CouponDataModel>>() {
            @Override
            public void onChanged(List<CouponDataModel> couponDataModels) {
                marketListAdapter.setData(couponDataModels);
                marketListAdapter.notifyDataSetChanged();
            }
        };

        mViewModel.getCouponModel().observe(getViewLifecycleOwner(), observer);
    }

}