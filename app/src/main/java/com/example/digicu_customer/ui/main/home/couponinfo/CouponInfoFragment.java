package com.example.digicu_customer.ui.main.home.couponinfo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.CouponDataModel;
import com.example.digicu_customer.data.dataset.CouponInfoDataModel;
import com.example.digicu_customer.data.dataset.RecordOfPurchaseDataModel;
import com.example.digicu_customer.general.GeneralVariable;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coupon_info, container, false);

        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // set RecyclerView
        recordRecyclerView = view.findViewById(R.id.record_recyclerview);
        recordRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        boxRecyclerView = view.findViewById(R.id.box_recyclerview);
        boxRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recordAdapter = new RecordAdapter(null);
        recordAdapter.setOnItemClickLister(new RecordAdapter.OnItemClickLister() {
            @Override
            public void onItemClick(View v, int pos, RecordOfPurchaseDataModel data) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("record", data);

                NavHostFragment.findNavController(CouponInfoFragment.this).navigate(R.id.action_couponInfoFragment_to_receiptFragment, bundle);
            }
        });

        recordRecyclerView.setAdapter(recordAdapter);

        boxAdapter = new BoxAdapter(null);
        boxRecyclerView.setAdapter(boxAdapter);
        //

        // set couponInfo ViewModel
        couponInfoViewModel = new ViewModelProvider(requireActivity()).get(CouponInfoViewModel.class);
        final Observer<CouponInfoDataModel> couponInfoDataModelObserver = new Observer<CouponInfoDataModel>() {
            @Override
            public void onChanged(CouponInfoDataModel couponInfoDataModel) {
                couponInfoViewModel.loadCouponInfoData();

                List<RecordOfPurchaseDataModel> recordOfPurchaseDataModelList = couponInfoViewModel.getRecordOfPurchaseData().getValue();
                List<CouponDataModel> couponDataModelList = couponInfoViewModel.getCouponData().getValue();

                if (recordOfPurchaseDataModelList != null) {
                    recordAdapter.setData(recordOfPurchaseDataModelList);
                    recordAdapter.notifyDataSetChanged();
                }

                if (couponDataModelList != null) {
                    boxAdapter.setData(couponDataModelList);
                    boxAdapter.notifyDataSetChanged();
                }

            }
        };

        couponInfoDataModel = (CouponInfoDataModel) getArguments().getSerializable("CouponInfo");
        Log.d(GeneralVariable.TAG, "__onStart: " + couponInfoDataModel);
        couponInfoViewModel.getCouponInfoDataModel().observe(requireActivity(), couponInfoDataModelObserver);
        couponInfoViewModel.getCouponInfoDataModel().setValue(couponInfoDataModel);
        //

        mTabLayout = view.findViewById(R.id.tab_layout);
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
    }
}