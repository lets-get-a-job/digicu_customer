package com.example.digicu_customer.ui.main.trade.tabs.couponlist;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.CouponDataModel;
import com.example.digicu_customer.ui.main.home.HomeAdapter;

import java.util.List;

public class CouponListFragment extends Fragment {
    private CouponListAdapter couponTradeListAdapter;
    private CouponListAdapter couponTradeReqListAdapter;

    private CouponListViewModel mViewModel;
    private RecyclerView tradeRecyclerView;
    private RecyclerView tradeReqRecyclerView;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coupon_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tradeRecyclerView = view.findViewById(R.id.coupon_sell_req_recyclerview);
        tradeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tradeReqRecyclerView = view.findViewById(R.id.coupon_buy_req_recyclerview);
        tradeReqRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        couponTradeListAdapter = new CouponListAdapter();
        couponTradeReqListAdapter = new CouponListAdapter();
        tradeRecyclerView.setAdapter(couponTradeListAdapter);
        tradeReqRecyclerView.setAdapter(couponTradeReqListAdapter);

        couponTradeListAdapter.setOnItemClickLister(new HomeAdapter.OnItemClickLister() {
            @Override
            public void onItemClick(View v, int pos, CouponDataModel data) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("couponData", data);
                bundle.putString("viewType", "trade_request");

                NavHostFragment.findNavController(CouponListFragment.this).navigate(R.id.action_page_2_to_couponInfoFragment3, bundle);
            }
        });

        couponTradeReqListAdapter.setOnItemClickLister(new HomeAdapter.OnItemClickLister() {
            @Override
            public void onItemClick(View v, int pos, CouponDataModel data) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("couponData", data);
                bundle.putString("viewType", "trade_request");

                NavHostFragment.findNavController(CouponListFragment.this).navigate(R.id.action_page_2_to_couponInfoFragment3, bundle);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CouponListViewModel.class);

        final Observer<List<CouponDataModel>> observerTrade = new Observer<List<CouponDataModel>>() {
            @Override
            public void onChanged(List<CouponDataModel> couponDataModels) {
                couponTradeListAdapter.setData(couponDataModels);
                couponTradeListAdapter.notifyDataSetChanged();
            }
        };

        final Observer<List<CouponDataModel>> observerTradeReq = new Observer<List<CouponDataModel>>() {
            @Override
            public void onChanged(List<CouponDataModel> couponDataModels) {
                couponTradeReqListAdapter.setData(couponDataModels);
                couponTradeReqListAdapter.notifyDataSetChanged();
            }
        };

        mViewModel.getTradingCouponModel().observe(getViewLifecycleOwner(), observerTrade);
        mViewModel.getTradingReqCouponModel().observe(getViewLifecycleOwner(), observerTradeReq);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.loadTradeCouponData();
        mViewModel.loadTradeReqCouponData();
    }
}
