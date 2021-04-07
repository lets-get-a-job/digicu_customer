package com.example.digicu_customer.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.digicu_customer.R;

import java.util.ArrayList;

public class MenuAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<String> mMenu;
    LayoutInflater mLayoutInflater;

    public MenuAdapter(Context mContext, ArrayList<String> mMenu) {
        this.mContext = mContext;
        this.mMenu = mMenu;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mMenu.size();
    }

    @Override
    public String getItem(int position) {
        return mMenu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemLayout = mLayoutInflater.inflate(R.layout.digicu_menu_list_view_item_layout, null);
        TextView menuNm = (TextView) itemLayout.findViewById(R.id.digicu_menu_lvitem_tv);

        menuNm.setText(mMenu.get(position));

        return itemLayout;
    }
}