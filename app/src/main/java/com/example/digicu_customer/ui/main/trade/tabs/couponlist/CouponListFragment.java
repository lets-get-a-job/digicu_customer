package com.example.digicu_customer.ui.main.trade.tabs.couponlist;

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
import android.widget.TextView;

import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.CouponDataModel;

import java.util.List;

public class CouponListFragment extends Fragment {
    private CouponListAdapter couponListAdapter;
    private CouponListViewModel mViewModel;
    private RecyclerView recyclerView;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coupon_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.coupon_list_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        couponListAdapter = new CouponListAdapter();
        recyclerView.setAdapter(couponListAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CouponListViewModel.class);

        final Observer<List<CouponDataModel>> observer = new Observer<List<CouponDataModel>>() {
            @Override
            public void onChanged(List<CouponDataModel> couponDataModels) {
                couponListAdapter.setData(couponDataModels);
                couponListAdapter.notifyDataSetChanged();
            }
        };

        mViewModel.getTradingCouponModel().observe(getViewLifecycleOwner(), observer);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}

//action_page_3_to_couponInfoFragment22