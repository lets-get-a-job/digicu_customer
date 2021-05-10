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
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.CouponInfoDataModel;
import com.example.digicu_customer.data.dataset.ShopDataModel;

import java.util.List;

import static com.example.digicu_customer.general.GeneralVariable.TAG;

public class CouponInfoFragment extends Fragment {
    ShopDataModel shopInfoDataModel;
    CouponInfoViewModel couponInfoViewModel;
    CouponInfoAdapter couponInfoAdapter;
    ImageButton closeBtn;
    TextView shop_name;
    TextView shop_owner;
    TextView shop_phone;
    TextView shop_address;
    TextView shop_homepage;
    RecyclerView recyclerView;

    public CouponInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        closeBtn = ((AppCompatActivity)getActivity()).findViewById(R.id.main_close_btn);
        couponInfoAdapter = new CouponInfoAdapter(null);

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

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(couponInfoAdapter);

        Log.d(TAG, "onResume: " + getArguments().getSerializable("shopDataModel").toString());
        shopInfoDataModel = (ShopDataModel) getArguments().getSerializable("shopDataModel");
        couponInfoViewModel = new CouponInfoViewModel(shopInfoDataModel);

        final Observer<List<CouponInfoDataModel>> couponObserver = new Observer<List<CouponInfoDataModel>>() {
            @Override
            public void onChanged(List<CouponInfoDataModel> couponInfoDataModels) {
                Log.d(TAG, "onChanged: " + couponInfoDataModels.size());
                couponInfoAdapter.setData(couponInfoDataModels);
                couponInfoAdapter.notifyDataSetChanged();
            }
        };

        couponInfoViewModel.getCouponInfoDataModel().observe(requireActivity(), couponObserver);

        shop_name.setText(shopInfoDataModel.getName());
        shop_owner.setText(shopInfoDataModel.getOwner());
        shop_phone.setText(shopInfoDataModel.getPhone());
        shop_address.setText(shopInfoDataModel.getAddress());
        shop_homepage.setText(shopInfoDataModel.getCompany_homepage());

        TextView title = ((AppCompatActivity)getActivity()).findViewById(R.id.main_title);
        title.setText(((AppCompatActivity)getActivity()).getString(R.string.coupon_info));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coupon_info, container, false);

        shop_name = view.findViewById(R.id.coupon_info_shop_name);
        shop_owner = view.findViewById(R.id.coupon_info_shop_owner);
        shop_phone = view.findViewById(R.id.coupon_info_shop_phone);
        shop_address = view.findViewById(R.id.coupon_info_shop_address);
        shop_homepage = view.findViewById(R.id.coupon_info_shop_homepage);
        recyclerView = view.findViewById(R.id.coupon_kind_recyclerview);

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