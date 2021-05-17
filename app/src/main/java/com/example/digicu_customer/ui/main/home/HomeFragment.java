package com.example.digicu_customer.ui.main.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.digicu_customer.data.dataset.CouponDataModel;
import com.example.digicu_customer.general.GeneralVariable;
import com.example.digicu_customer.ui.main.home.couponinfo.CouponInfoFragment;
import com.example.digicu_customer.R;
import com.google.android.material.card.MaterialCardView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

public class HomeFragment extends Fragment {
    HomeViewModel homeViewModel;
    RecyclerView mRecyclerView;
    CouponInfoFragment couponInfoFragment;
    MaterialCardView materialCardView;
    HomeAdapter homeAdapter;
    AppCompatActivity mainActivity;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        homeViewModel.loadCouponInfo();
        mainActivity = ((AppCompatActivity)getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        TextView title = mainActivity.findViewById(R.id.main_title);
        title.setText(getString(R.string.coupon_list));

//        imageExtension.setVisibility(View.VISIBLE);
//        actionBar.setDisplayHomeAsUpEnabled(false);
//        actionBar.setTitle(getString(R.string.coupon_list));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeAdapter = new HomeAdapter();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(GeneralVariable.TAG, "onActivityResult: fragment");

        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result == null) {
                Toast.makeText(getContext(), "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();

                // Update Data
                homeViewModel.loadCouponInfo();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        final Observer<List<CouponDataModel>> couponObserver = new Observer<List<CouponDataModel>>() {
            @Override
            public void onChanged(List<CouponDataModel> couponInfo) {
                homeAdapter.setData(couponInfo);
                homeAdapter.notifyDataSetChanged();
            }
        };
        homeViewModel.getCouponInfo().observe(requireActivity(), couponObserver);

        mRecyclerView = view.findViewById(R.id.digicu_home_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(homeAdapter);

        couponInfoFragment = new CouponInfoFragment();

//        materialCardView = view.findViewById(R.id.digicu_home_add_coupon);
//        materialCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // add Coupon data to server
//                QrGenerator qrGenerator = new QrGenerator(getActivity(), HomeFragment.this);
//                qrGenerator.startQRCode();
//            }
//        });

        homeAdapter.setOnItemClickLister(new HomeAdapter.OnItemClickLister() {
            @Override
            public void onItemClick(View v, int pos, CouponDataModel data) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("couponData", data);

                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_page_1_to_couponInfoFragment, bundle);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
//        imageExtension.setVisibility(View.GONE);
    }
}