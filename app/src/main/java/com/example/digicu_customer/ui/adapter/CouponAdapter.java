package com.example.digicu_customer.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digicu_customer.GeneralVariable;
import com.example.digicu_customer.R;
import com.example.digicu_customer.dataset.CouponInfo;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.ViewHolder> {
    public interface OnItemClickLister {
        void onItemClick(View v, int pos, CouponInfo data);
    }

    private CouponInfo[] data;
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

                        Log.d(GeneralVariable.TAG, data[pos].toString());
                        if (onItemClickLister != null)
                            onItemClickLister.onItemClick(view, pos, data[pos]);
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

    public CouponAdapter(CouponInfo[] data) {
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
        CouponInfo couponInfo = data[position];

        holder.getShopName().setText(couponInfo.getShop().getName());
        if (couponInfo.getType() == CouponInfo.CouponType.STAMP)
            holder.getMileage().setText(couponInfo.getStampCnt() + "/" + couponInfo.getCountCanBeTransfer());
        else
            holder.getMileage().setText(couponInfo.getMileage() + " points");
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
}
