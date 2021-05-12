package com.example.digicu_customer.ui.main.home.couponinfo;

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

import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.CouponInfoDataModel;
import com.example.digicu_customer.data.dataset.ShopDataModel;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;

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
    TextView couponCnt;
    MaterialCardView mainCard;
    ImageView stamps[];

    // Todo : set Stamps count
    int st_cnt = 8;

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
        TextView title = ((AppCompatActivity)getActivity()).findViewById(R.id.main_title);
        title.setText(((AppCompatActivity)getActivity()).getString(R.string.coupon_info));
        closeBtn.setVisibility(View.VISIBLE);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(couponInfoAdapter);

        couponInfoAdapter.setOnItemClickLister(new CouponInfoAdapter.OnItemClickLister() {
            @Override
            public void onItemClick(View v, int pos, CouponInfoDataModel data) {
                if(data.getGoal() > st_cnt) {
                    Snackbar snackbar = Snackbar.make(((AppCompatActivity)getActivity()).findViewById(R.id.main_activity_layout),
                            data.getName() +"을 발급하기 위해서\n" + (data.getGoal() - st_cnt) + "개의 도장이 모자랍니다.", Snackbar.LENGTH_INDEFINITE);
                    snackbar.setActionTextColor(getResources().getColor(R.color.digicu_base_primary_color));
//                    snackbar.setBackgroundTint(getResources().getColor(R.color.digicu_base_primary_color));

                    snackbar.setAction("확인", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    snackbar.dismiss();
                                }
                            }).show();

                    return;
                }

                // Todo Coupon publish

            }
        });

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

        mainCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                couponCnt.setVisibility(View.VISIBLE);
                return true;
            }
        });

        couponCnt.setText(this.st_cnt + "/10");

        couponCnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                couponCnt.setVisibility(View.GONE);
            }
        });


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
        couponCnt = view.findViewById(R.id.coupon_info_coupon_count);
        mainCard = view.findViewById(R.id.coupon_info_coupon_card);

        stamps = new ImageView[10];

        int stamps_id[] = {R.id.coupon_stamp_0,R.id.coupon_stamp_1,R.id.coupon_stamp_2,R.id.coupon_stamp_3,R.id.coupon_stamp_4,
                            R.id.coupon_stamp_5,R.id.coupon_stamp_6,R.id.coupon_stamp_7,R.id.coupon_stamp_8,R.id.coupon_stamp_9};

        for (int i = 0; i < 10; i++) {
            stamps[i] = view.findViewById(stamps_id[i]);
            if(i < st_cnt)
                stamps[i].setVisibility(View.VISIBLE);
        }

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