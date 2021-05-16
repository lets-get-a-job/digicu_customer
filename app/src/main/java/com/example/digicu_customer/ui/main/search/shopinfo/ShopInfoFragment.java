package com.example.digicu_customer.ui.main.search.shopinfo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.CouponInfoDataModel;
import com.example.digicu_customer.data.dataset.ShopDataModel;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import static com.example.digicu_customer.general.GeneralVariable.TAG;

public class ShopInfoFragment extends Fragment {
    ShopDataModel shopInfoDataModel;
    ShopInfoViewModel shopInfoViewModel;
    ShopInfoAdapter shopInfoAdapter;
    ImageButton closeBtn;
    TextView shop_name;
    TextView shop_owner;
    TextView shop_phone;
    TextView shop_address;
    TextView shop_homepage;
    RecyclerView recyclerView;
    MaterialCardView mainCard;
    ImageView mShopLogoImageView;

    public ShopInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        closeBtn = ((AppCompatActivity)getActivity()).findViewById(R.id.main_close_btn);
        shopInfoAdapter = new ShopInfoAdapter(null);

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
        TextView title = ((AppCompatActivity)getActivity()).findViewById(R.id.main_title);
        title.setText(((AppCompatActivity)getActivity()).getString(R.string.shop_info));
        closeBtn.setVisibility(View.VISIBLE);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(shopInfoAdapter);

        Log.d(TAG, "onResume: " + getArguments().getSerializable("shopDataModel").toString());
        shopInfoDataModel = (ShopDataModel) getArguments().getSerializable("shopDataModel");
        shopInfoViewModel = new ShopInfoViewModel(shopInfoDataModel);

        final Observer<List<CouponInfoDataModel>> couponObserver = new Observer<List<CouponInfoDataModel>>() {
            @Override
            public void onChanged(List<CouponInfoDataModel> couponInfoDataModels) {
                Log.d(TAG, "onChanged: " + couponInfoDataModels.size());
                shopInfoAdapter.setData(couponInfoDataModels);
                shopInfoAdapter.notifyDataSetChanged();
            }
        };

        shopInfoViewModel.getCouponInfoDataModel().observe(requireActivity(), couponObserver);

        shop_name.setText(shopInfoDataModel.getName());
        shop_owner.setText(shopInfoDataModel.getOwner());
        shop_phone.setText(shopInfoDataModel.getPhone());
        shop_address.setText(shopInfoDataModel.getAddress());
        shop_homepage.setText(shopInfoDataModel.getCompany_homepage());

        CircularProgressDrawable drawable = new CircularProgressDrawable(getContext());
        drawable.setColorSchemeColors(R.color.design_default_color_primary, R.color.design_default_color_primary_dark, R.color.white);
        drawable.setCenterRadius(30f);
        drawable.setStrokeWidth(5f);
        drawable.start();

        try {
            Glide.with(getContext()).load(shopInfoDataModel.getLogo_url())
                    .fitCenter()
                    .placeholder(drawable)
                    .error(R.drawable.ic_baseline_error_40)
                    .into(mShopLogoImageView);
        } catch (Exception e) {
            Log.d(TAG, "onResume: " + e.getMessage());
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_info, container, false);

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

        shop_name = view.findViewById(R.id.coupon_info_shop_name);
        shop_owner = view.findViewById(R.id.coupon_info_shop_owner);
        shop_phone = view.findViewById(R.id.coupon_info_shop_phone);
        shop_address = view.findViewById(R.id.coupon_info_shop_address);
        shop_homepage = view.findViewById(R.id.coupon_info_shop_homepage);
        recyclerView = view.findViewById(R.id.coupon_kind_recyclerview);
        mShopLogoImageView = view.findViewById(R.id.shop_logo_img);
        mainCard = view.findViewById(R.id.coupon_info_coupon_card);
    }
}