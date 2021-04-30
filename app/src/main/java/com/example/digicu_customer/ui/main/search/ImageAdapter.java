package com.example.digicu_customer.ui.main.search;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.ShopDataModel;
import com.example.digicu_customer.general.GeneralVariable;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.example.digicu_customer.general.GeneralVariable.TAG;

public class ImageAdapter extends BaseAdapter {
    List<ShopDataModel> shopDataModels;

    public ImageAdapter() {
        this.shopDataModels = new ArrayList<>();
    }

    public ImageAdapter(List<ShopDataModel> shopDataModels) {
        this.shopDataModels = shopDataModels;
        if (shopDataModels == null) {
            this.shopDataModels = new ArrayList<>();
        }
    }

    public void setShopDataModels(List<ShopDataModel> shopDataModels) {
        this.shopDataModels = shopDataModels;
    }

    @Override
    public int getCount() {
        return shopDataModels.size();
    }

    @Override
    public ShopDataModel getItem(int i) {
        return shopDataModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        ShopDataModel shopDataModel = shopDataModels.get(i);

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.digicu_search_grid_view_item_layout, viewGroup, false);
        }

        Log.d(TAG, "getView: " + shopDataModel.toString());

        ImageView img = view.findViewById(R.id.search_shop_image);
        TextView tv = view.findViewById(R.id.search_shop_name);

        // create a ProgressDrawable object which we will show as placeholder
        CircularProgressDrawable drawable = new CircularProgressDrawable(context);
        drawable.setColorSchemeColors(R.color.design_default_color_primary, R.color.design_default_color_primary_dark, R.color.white);
        drawable.setCenterRadius(30f);
        drawable.setStrokeWidth(5f);
        // set all other properties as you would see fit and start it
        drawable.start();

        try {
            Glide.with(context).load(shopDataModel.getLogo_url())
                               .fitCenter()
                               .placeholder(drawable)
                               .error(R.drawable.ic_baseline_error_40)
                               .into(img);
        } catch (Exception e) {
            Log.d(TAG, "getView: " + e.getMessage());
        }

        tv.setText(shopDataModel.getName());

        return view;
    }
}
