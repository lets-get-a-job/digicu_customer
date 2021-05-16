package com.example.digicu_customer.ui.main.home.savingcoupon;

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

import java.util.ArrayList;
import java.util.List;

public class CouponInfoAdapter extends RecyclerView.Adapter<CouponInfoAdapter.ViewHolder> {
    public interface OnItemClickLister {
        void onItemClick(View v, int pos, CouponInfoDataModel data);
    }

    private List<CouponInfoDataModel> data;
    private OnItemClickLister onItemClickLister;


    public void setOnItemClickLister(OnItemClickLister onItemClickLister) {
        this.onItemClickLister = onItemClickLister;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView couponName;
        private final TextView couponType;
        private final TextView needStamp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.couponName = itemView.findViewById(R.id.item_coupon_name);
            this.couponType = itemView.findViewById(R.id.discount_type);
            this.needStamp = itemView.findViewById(R.id.need_stamp_count);

            itemView.findViewById(R.id.card_view_item).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){

                        Log.d(GeneralVariable.TAG, data.get(pos).toString());
                        if (onItemClickLister != null)
                            onItemClickLister.onItemClick(view, pos, data.get(pos));
                    }
                }
            });
        }

        public TextView getCouponName() {
            return couponName;
        }

        public TextView getCouponType() {
            return couponType;
        }

        public TextView getNeedStamp() {
            return needStamp;
        }
    }

    public void setData(List<CouponInfoDataModel> data) {
        this.data = data;
    }

    public CouponInfoAdapter(List<CouponInfoDataModel> data) {
        this.data = data;
        if(this.data == null) {
            this.data = new ArrayList<>();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.digicu_coupon_info_recyclerview_item_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CouponInfoDataModel couponInfoDataModel = data.get(position);

        holder.getCouponName().setText(couponInfoDataModel.getName());
        holder.getCouponType().setText(couponInfoDataModel.getValue() + "원 " + couponInfoDataModel.getCouponType().name());
        holder.getNeedStamp().setText(String.valueOf(couponInfoDataModel.getGoal()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
