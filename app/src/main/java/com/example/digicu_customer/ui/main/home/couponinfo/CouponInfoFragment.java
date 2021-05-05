package com.example.digicu_customer.ui.main.home.couponinfo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
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

import static com.example.digicu_customer.general.GeneralVariable.TAG;

public class CouponInfoFragment extends Fragment {
    TabLayout mTabLayout;
    CouponInfoDataModel couponInfoDataModel;
    ImageButton closeBtn;

    public CouponInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        closeBtn = ((AppCompatActivity)getActivity()).findViewById(R.id.main_close_btn);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment navHostFragment = (NavHostFragment) ((AppCompatActivity)getActivity()).getSupportFragmentManager().findFragmentById(R.id.digicu_main_nav_host);
                NavController navController = navHostFragment.getNavController();
                navController.navigateUp();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        closeBtn.setVisibility(View.VISIBLE);

        Log.d(TAG, "onResume: " + getArguments().getSerializable("CouponInfo").toString());
        couponInfoDataModel = (CouponInfoDataModel) getArguments().getSerializable("CouponInfo");

        TextView title = ((AppCompatActivity)getActivity()).findViewById(R.id.main_title);
        title.setText(((AppCompatActivity)getActivity()).getString(R.string.coupon_info));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coupon_info, container, false);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        closeBtn.setVisibility(View.GONE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}