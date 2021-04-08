package com.example.digicu_customer.ui.main.couponinfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.CouponInfoDataModel;
import com.google.android.material.tabs.TabLayout;

public class CouponInfoFragment extends Fragment {
    CouponInfoViewModel couponInfoViewModel;
    CouponInfoDataModel couponInfoDataModel;

    TabLayout mTabLayout;
    RecyclerView recordRecyclerView;
    RecordAdapter recordAdapter;
    RecyclerView boxRecyclerView;
    BoxAdapter boxAdapter;

    public CouponInfoFragment() {
        // Required empty public constructor
    }

    public void setCouponInfoDataModel(CouponInfoDataModel CouponInfoDataModel) {
        this.couponInfoDataModel = CouponInfoDataModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coupon_info, container, false);

        couponInfoViewModel = new ViewModelProvider(requireActivity()).get(CouponInfoViewModel.class);
        final Observer<CouponInfoDataModel> couponInfoDataModelObserver = new Observer<CouponInfoDataModel>() {
            @Override
            public void onChanged(CouponInfoDataModel couponInfoDataModel) {

                recordAdapter.setData(couponInfoViewModel.getRecordOfPurchaseData().getValue());
            }
        };
        couponInfoViewModel.getCouponInfoDataModel(couponInfoDataModel).observe(requireActivity(), couponInfoDataModelObserver);

        mTabLayout = view.findViewById(R.id.tab_layout);
        recordRecyclerView = view.findViewById(R.id.record_recyclerview);
        recordRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        boxRecyclerView = view.findViewById(R.id.box_recyclerview);
        boxRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recordAdapter = new RecordAdapter(null);
        recordRecyclerView.setAdapter(recordAdapter);

        boxAdapter = new BoxAdapter(null);
        boxRecyclerView.setAdapter(boxAdapter);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                Log.d(GeneralVariable.TAG, "onTabSelected: " + tab.getPosition());

                if(tab.getPosition() == 0) {
                    recordRecyclerView.setVisibility(View.VISIBLE);
                    boxRecyclerView.setVisibility(View.GONE);
                } else if(tab.getPosition() == 1) {
                    recordRecyclerView.setVisibility(View.GONE);
                    boxRecyclerView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        couponInfoViewModel.loadCouponInfoData();
    }

}