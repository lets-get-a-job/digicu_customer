package com.example.digicu_customer.ui.main.home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
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
    CouponInfoFragment couponInfoFragment;
    MaterialCardView materialCardView;
    HomeAdapter homeAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        homeViewModel.loadCouponInfo();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeAdapter = new HomeAdapter();
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
                homeAdapter.setData(couponInfo);
                homeAdapter.notifyDataSetChanged();
            }
        };
        homeViewModel.getCouponInfo().observe(requireActivity(), couponInfoObserver);

        mRecyclerView = view.findViewById(R.id.digicu_home_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(homeAdapter);

        couponInfoFragment = new CouponInfoFragment();

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
                Bundle bundle = new Bundle();
                bundle.putSerializable("CouponInfo", data);

                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_page_1_to_couponInfoFragment, bundle);
            }
        });

        return view;
    }
}