package com.example.digicu_customer.ui.main.trade.tabs.market;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.CouponDataModel;
import com.example.digicu_customer.data.dataset.CouponInfoDataModel;
import com.example.digicu_customer.ui.main.home.HomeAdapter;
import com.example.digicu_customer.ui.main.home.HomeFragment;

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

        marketListAdapter.setOnItemClickLister(new HomeAdapter.OnItemClickLister() {
            @Override
            public void onItemClick(View v, int pos, CouponDataModel data) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("couponData", data);
                bundle.putString("viewType", "trade_request");

                NavHostFragment.findNavController(MarketFragment.this).navigate(R.id.action_page_2_to_couponInfoFragment3, bundle);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.loadCouponData();
    }

}