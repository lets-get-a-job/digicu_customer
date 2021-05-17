package com.example.digicu_customer.ui.main.home.couponinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.CouponDataModel;

public class CouponInfoAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    CouponDataModel couponDataModel;

    public CouponInfoAdapter(Context context, CouponDataModel couponDataModel) {
        this.context = context;
        this.couponDataModel = couponDataModel;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return couponDataModel.getGoal();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
            view = layoutInflater.inflate(R.layout.digicu_coupon_info_gridview_item_layout, null);

        ImageView imageView = view.findViewById(R.id.stampImageView);

        if(i < couponDataModel.getCount()) {
            imageView.setImageResource(R.drawable.stamp1);
        } else if(i == couponDataModel.getGoal()-1) {
            imageView.setImageResource(R.drawable.stamp2);
        }

        return view;
    }
}
