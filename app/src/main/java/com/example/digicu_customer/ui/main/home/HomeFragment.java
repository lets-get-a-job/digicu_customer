package com.example.digicu_customer.ui.main.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.digicu_customer.general.GeneralVariable;
import com.example.digicu_customer.ui.main.couponinfo.CouponInfoFragment;
import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.CouponInfoDataModel;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class HomeFragment extends Fragment {
    HomeViewModel homeViewModel;
    RecyclerView mRecyclerView;
    CouponInfoFragment fragmentBottomSheet;
    MaterialCardView materialCardView;
    HomeAdapter homeAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        homeViewModel.loadCouponInfo();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        final Observer<List<CouponInfoDataModel>> couponInfoObserver = new Observer<List<CouponInfoDataModel>>() {
            @Override
            public void onChanged(List<CouponInfoDataModel> couponInfo) {
                Log.d(GeneralVariable.TAG, "onChanged: " + couponInfo.size());
                homeAdapter.setData(couponInfo);
                homeAdapter.notifyDataSetChanged();
            }
        };
        homeViewModel.getCouponInfo().observe(requireActivity(), couponInfoObserver);


        homeAdapter = new HomeAdapter();
        mRecyclerView = view.findViewById(R.id.digicu_home_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(homeAdapter);

        fragmentBottomSheet = new CouponInfoFragment();

        materialCardView = view.findViewById(R.id.digicu_home_add_coupon);
        materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add Coupon data to server

                // Update Data
                homeViewModel.loadCouponInfo();
            }
        });

        homeAdapter.setOnItemClickLister(new HomeAdapter.OnItemClickLister() {
            @Override
            public void onItemClick(View v, int pos, CouponInfoDataModel data) {
//                fragmentBottomSheet.setCouponInfo(data);
//                fragmentBottomSheet.show(requireFragmentManager(), fragmentBottomSheet.getTag());
            }
        });

        return view;
    }
}