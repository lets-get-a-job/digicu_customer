package com.example.digicu_customer.ui.main.trade;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.digicu_customer.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TradeFragment extends Fragment {
    FragmentAdapter fragmentAdapter;
    ViewPager2 viewPager2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trade, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentAdapter = new FragmentAdapter(this);
        viewPager2 = view.findViewById(R.id.trade_view_pager);
        viewPager2.setAdapter(fragmentAdapter);

        TabLayout tabLayout = view.findViewById(R.id.trade_tab_layout);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            tab.setText(fragmentAdapter.title[position]);
        }).attach();
    }
}