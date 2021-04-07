package com.example.digicu_customer.ui.couponinfo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digicu_customer.GeneralVariable;
import com.example.digicu_customer.R;
import com.example.digicu_customer.dataset.Coupon;
import com.example.digicu_customer.dataset.CouponInfo;
import com.example.digicu_customer.dataset.Product;
import com.example.digicu_customer.dataset.RecordOfPurchase;
import com.example.digicu_customer.ui.adapter.BoxAdapter;
import com.example.digicu_customer.ui.adapter.RecordAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Date;

public class CouponInfoFragment extends Fragment {
    TabLayout mTabLayout;
    CouponInfo couponInfo;

    RecyclerView recordRecyclerView;
    RecordAdapter recordAdapter;
    RecyclerView boxRecyclerView;
    BoxAdapter boxAdapter;

    public CouponInfoFragment() {
        // Required empty public constructor
    }

    public void setCouponInfo(CouponInfo couponInfo) {
        this.couponInfo = couponInfo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coupon_info, container, false);

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

        Log.d(GeneralVariable.TAG, "onStart: " + couponInfo.toString());

        // temporary data
        Product products1[] = new Product[2];
        products1[0] = new Product("A", 1000, 3);
        products1[1] = new Product("B", 2000, 1);
        Product products2[] = new Product[2];
        products2[0] = new Product("C", 1000, 3);
        products2[1] = new Product("D", 2000, 1);

        RecordOfPurchase recordOfPurchases[] = new RecordOfPurchase[5];
        recordOfPurchases[0] = new RecordOfPurchase(couponInfo, 1, new Date(), products1);
        recordOfPurchases[1] = new RecordOfPurchase(couponInfo, 2, new Date(), products2);
        recordOfPurchases[2] = new RecordOfPurchase(couponInfo, 3, new Date(), products1);
        recordOfPurchases[3] = new RecordOfPurchase(couponInfo, 4, new Date(), products2);
        recordOfPurchases[4] = new RecordOfPurchase(couponInfo, 5, new Date(), products1);

        Coupon[] coupons = new Coupon[5];
        coupons[0] = new Coupon(couponInfo.getShop(), 1,"무적권올해취업 7000원 할인 쿠폰", new Date(), true);
        coupons[1] = new Coupon(couponInfo.getShop(), 1,"무적권올해취업 3000원 할인 쿠폰", new Date(), true);
        coupons[2] = new Coupon(couponInfo.getShop(), 1,"무적권올해취업 5000원 할인 쿠폰", new Date(), true);
        coupons[3] = new Coupon(couponInfo.getShop(), 1,"무적권올해취업 5000원 할인 쿠폰", new Date(), true);
        coupons[4] = new Coupon(couponInfo.getShop(), 1,"무적권올해취업 5000원 할인 쿠폰", new Date(), true);
        //----------------

//            recordRecyclerView.setVisibility(View.VISIBLE);

        recordAdapter.setData(recordOfPurchases);
        recordAdapter.notifyDataSetChanged();

        boxAdapter.setData(coupons);
        boxAdapter.notifyDataSetChanged();

    }

}