package com.example.digicu_customer.ui.main.coupon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.CouponDataModel;
import com.example.digicu_customer.ui.main.home.HomeAdapter;

import java.util.List;

import static com.example.digicu_customer.general.GeneralVariable.TAG;

public class CouponFragment extends Fragment {
    private AppCompatActivity mainActivity;
    private LinearLayout section01;
    private LinearLayout section01_title;
    private RecyclerView section01_recyclerView;
    private ImageButton section01_img;
    private LinearLayout section02;
    private LinearLayout section02_title;
    private RecyclerView section02_recyclerView;
    private ImageButton section02_img;
    private CouponViewModel mViewModel;

    private CouponListAdapter availableCouponListAdapter;
    private CouponListAdapter unavailableCouponListAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.coupon_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        section01 = view.findViewById(R.id.section01);
        section02 = view.findViewById(R.id.section02);
        section01_title = view.findViewById(R.id.section01_title);
        section02_title = view.findViewById(R.id.section02_title);
        section01_recyclerView = view.findViewById(R.id.coupon_section1_recyclerview);
        section02_recyclerView = view.findViewById(R.id.coupon_section2_recyclerview);
        section01_img = view.findViewById(R.id.section01_expand_img);
        section02_img = view.findViewById(R.id.section02_expand_img);

        section01_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(section01_recyclerView.getVisibility() == View.GONE) {
                    section01_recyclerView.setVisibility(View.VISIBLE);
                    section01_img.setImageResource(R.drawable.ic_baseline_expand_less_24);
                } else {
                    section01_recyclerView.setVisibility(View.GONE);
                    section01_img.setImageResource(R.drawable.ic_baseline_expand_more_24);
                }
            }
        });

        section02_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(section02_recyclerView.getVisibility() == View.GONE) {
                    section02_recyclerView.setVisibility(View.VISIBLE);
                    section02_img.setImageResource(R.drawable.ic_baseline_expand_less_24);
                } else {
                    section02_recyclerView.setVisibility(View.GONE);
                    section02_img.setImageResource(R.drawable.ic_baseline_expand_more_24);
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CouponViewModel.class);
        availableCouponListAdapter = new CouponListAdapter();
        section01_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        section01_recyclerView.setAdapter(availableCouponListAdapter);
        unavailableCouponListAdapter = new CouponListAdapter();
        section02_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        section02_recyclerView.setAdapter(unavailableCouponListAdapter);

        HomeAdapter.OnItemClickLister onItemClickLister = new HomeAdapter.OnItemClickLister() {
            @Override
            public void onItemClick(View v, int pos, CouponDataModel data) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("couponData", data);

                NavHostFragment.findNavController(CouponFragment.this).navigate(R.id.action_page_3_to_couponInfoFragment22, bundle);
            }
        };

        availableCouponListAdapter.setOnItemClickLister(onItemClickLister);
        unavailableCouponListAdapter.setOnItemClickLister(onItemClickLister);

        final Observer<List<CouponDataModel>> availableCouponObserver = new Observer<List<CouponDataModel>>() {
            @Override
            public void onChanged(List<CouponDataModel> couponDataModels) {
                availableCouponListAdapter.setData(couponDataModels);
                availableCouponListAdapter.notifyDataSetChanged();
            }
        };

        final Observer<List<CouponDataModel>> unavailableCouponObserver = new Observer<List<CouponDataModel>>() {
            @Override
            public void onChanged(List<CouponDataModel> couponDataModels) {
                Log.d(TAG, "onChanged: " + couponDataModels.toString());
                unavailableCouponListAdapter.setData(couponDataModels);
                unavailableCouponListAdapter.notifyDataSetChanged();
            }
        };

        mViewModel.getAvailableCouponModel().observe(getViewLifecycleOwner(), availableCouponObserver);
        mViewModel.getUnavailableCouponModel().observe(getViewLifecycleOwner(), unavailableCouponObserver);
    }

    @Override
    public void onStart() {
        super.onStart();
        mainActivity = ((AppCompatActivity)getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        TextView title = mainActivity.findViewById(R.id.main_title);
        title.setText(getString(R.string.coupon_tab_item));
    }

}