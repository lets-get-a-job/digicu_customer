package com.example.digicu_customer.ui.main.search;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.ShopDataModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.digicu_customer.general.GeneralVariable.TAG;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    public interface OnItemClickLister {
        void onItemClick(View v, int pos, ShopDataModel shopDataModel);//, CouponInfoDataModel data);
    }

    List<ShopDataModel> shopDataModels;
    private OnItemClickLister onItemClickLister;
    private Context context;

    public void setOnItemClickLister(OnItemClickLister onItemClickLister) {
        this.onItemClickLister = onItemClickLister;
    }

    public ImageAdapter(Context context) {
        this.shopDataModels = new ArrayList<>();
        this.context = context;
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView img;
        private final TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.search_shop_image);
            tv = itemView.findViewById(R.id.search_shop_name);

            itemView.findViewById(R.id.digicu_search_shop_card).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        if (onItemClickLister != null)
                            onItemClickLister.onItemClick(view, pos, shopDataModels.get(pos));
                    }
                }
            });
        }

        public ImageView getImg() {
            return img;
        }

        public TextView getTv() {
            return tv;
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.digicu_search_recyclerview_item_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShopDataModel shopDataModel = shopDataModels.get(position);

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
                    .into(holder.getImg());
        } catch (Exception e) {
//            Log.d(TAG, "getView: " + e.getMessage());
        }

        Log.d(TAG, "onBindViewHolder: " + shopDataModel.toString());

        holder.getTv().setText(shopDataModel.getName());
    }

    @Override
    public int getItemCount() {
        return shopDataModels.size();
    }

}
