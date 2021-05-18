package com.example.digicu_customer.ui.main.trade;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.digicu_customer.ui.main.trade.tabs.market.MarketFragment;
import com.example.digicu_customer.ui.main.trade.tabs.couponlist.CouponListFragment;
import com.example.digicu_customer.ui.main.trade.tabs.tradelog.TradeLogFragment;

public class FragmentAdapter extends FragmentStateAdapter {
    Fragment[] fragments = {new MarketFragment(), new CouponListFragment(), new TradeLogFragment()};
    String[] title = {"장터", "등록된 쿠폰", "거래 기록"};

    public FragmentAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = fragments[position];

        return fragment;
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }


}
