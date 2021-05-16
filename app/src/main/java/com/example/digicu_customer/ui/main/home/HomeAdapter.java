package com.example.digicu_customer.ui.main.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digicu_customer.data.dataset.CouponDataModel;
import com.example.digicu_customer.data.dataset.ShopDataModel;
import com.example.digicu_customer.general.GeneralVariable;
import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.CouponInfoDataModel;
import com.example.digicu_customer.util.qr_generator.CustomDate;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    public interface OnItemClickLister {
        void onItemClick(View v, int pos, CouponDataModel data);
    }

    private List<CouponDataModel> data;
    private OnItemClickLister onItemClickLister;

    public void setOnItemClickLister(OnItemClickLister onItemClickLister) {
        this.onItemClickLister = onItemClickLister;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView couponName;
        private final TextView createDate;
        private final TextView mileage;
        private final TextView expiration_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.couponName = itemView.findViewById(R.id.coupon_name);
            this.createDate = itemView.findViewById(R.id.createDate);
            this.mileage = itemView.findViewById(R.id.mileage);
            this.expiration_date = itemView.findViewById(R.id.expiration_date);

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

        public TextView getCreateDate() {
            return createDate;
        }

        public TextView getMileage() {
            return mileage;
        }

        public TextView getExpiration_date() {
            return expiration_date;
        }
    }

    public HomeAdapter() {
        this.data = new ArrayList<>();
    }

    public HomeAdapter(List<CouponDataModel> data) {
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
        CouponDataModel couponDataModel = data.get(position);

        holder.getCouponName().setText(couponDataModel.getName());
        holder.getCreateDate().setText(CustomDate.getDigicuDateFormat().format(couponDataModel.getCreatedAt()));
        holder.getExpiration_date().setText("만료일 : " + CustomDate.getDigicuDateFormat().format(couponDataModel.getExpirationDate()));
        holder.getMileage().setText(couponDataModel.getCount() + "/" + couponDataModel.getGoal());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<CouponDataModel> data) {
        this.data = data;
    }
}
