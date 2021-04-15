package com.example.digicu_customer.ui.main.trade.tabs.couponlist;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.CouponInfoDataModel;
import com.example.digicu_customer.general.GeneralVariable;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CouponListAdapter extends RecyclerView.Adapter<CouponListAdapter.ViewHolder> {
    public interface OnItemClickLister {
        void onItemClick(View v, int pos);//, CouponInfoDataModel data);
    }

    private List<CouponInfoDataModel> data;
    private OnItemClickLister onItemClickLister;

    public void setOnItemClickLister(OnItemClickLister onItemClickLister) {
        this.onItemClickLister = onItemClickLister;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView shopName;
        private final TextView mileage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.shopName = itemView.findViewById(R.id.shop_name);
            this.mileage = itemView.findViewById(R.id.mileage);

            itemView.findViewById(R.id.card_view_item).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){

                        Log.d(GeneralVariable.TAG, data.get(pos).toString());
                        if (onItemClickLister != null)
                            onItemClickLister.onItemClick(view, pos);//, data.get(pos));
                    }
                }
            });
        }

        public TextView getShopName() {
            return shopName;
        }

        public TextView getMileage() {
            return mileage;
        }
    }

    public CouponListAdapter() {
        this.data = new ArrayList<>();
    }

    public CouponListAdapter(List<CouponInfoDataModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.digicu_home_recyclerview_item_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CouponInfoDataModel couponInfoDataModel = data.get(position);

        holder.getShopName().setText(couponInfoDataModel.getShopDataModel().getName());
        if (couponInfoDataModel.getType() == CouponInfoDataModel.CouponType.STAMP) {
            holder.getMileage().setText(0 + "/" + couponInfoDataModel.getCountCanBeTransfer());
        } else {
            holder.getMileage().setText(NumberFormat.getInstance(Locale.getDefault()).format(0) + " points");
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<CouponInfoDataModel> data) {
        this.data = data;
    }
}
