package com.example.digicu_customer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.digicu_customer.adapter.CouponAdapter;
import com.example.digicu_customer.dataset.CouponInfo;
import com.example.digicu_customer.dataset.Shop;

public class HomeFragment extends Fragment {
    RecyclerView mRecyclerView;
    CouponAdapter couponAdapter;
    CouponInfoFragmentBottomSheet fragmentBottomSheet;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Shop shops[] = new Shop[5];
        shops[0] = new Shop("test1", "상도동1", "010-1234-1234");
        shops[1] = new Shop("test2", "상도동2", "010-1234-1234");
        shops[2] = new Shop("test3", "상도동3", "010-1234-1234");
        shops[3] = new Shop("test4", "상도동4", "010-1234-1234");
        shops[4] = new Shop("test5", "상도동5", "010-1234-1234");

        CouponInfo couponInfos[] = new CouponInfo[5];
        couponInfos[0] = new CouponInfo(shops[0], CouponInfo.CouponType.STAMP, 0, 5, 10);
        couponInfos[1] = new CouponInfo(shops[1], CouponInfo.CouponType.MILEAGE, 1750, 0, 0);
        couponInfos[2] = new CouponInfo(shops[2], CouponInfo.CouponType.STAMP, 0, 2, 15);
        couponInfos[3] = new CouponInfo(shops[3], CouponInfo.CouponType.STAMP, 0, 4, 10);
        couponInfos[4] = new CouponInfo(shops[4], CouponInfo.CouponType.MILEAGE, 5003, 0, 0);

        // read user data and set list;
        couponAdapter = new CouponAdapter(couponInfos);

        mRecyclerView = view.findViewById(R.id.digicu_home_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(couponAdapter);

//        requireFragmentManager()

        fragmentBottomSheet = new CouponInfoFragmentBottomSheet();

        couponAdapter.setOnItemClickLister(new CouponAdapter.OnItemClickLister() {
            @Override
            public void onItemClick(View v, int pos, CouponInfo data) {
                fragmentBottomSheet.setCouponInfo(data);
                fragmentBottomSheet.show(requireFragmentManager(), fragmentBottomSheet.getTag());
            }
        });

        return view;
    }
}