package com.example.digicu_customer.ui.main.couponinfo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.CouponDataModel;
import com.example.digicu_customer.data.dataset.CouponInfoDataModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BoxAdapter extends RecyclerView.Adapter<BoxAdapter.ViewHolder> {
    public interface OnItemClickLister {
        void onItemClick(View v, int pos, CouponInfoDataModel data);
    }

    private List<CouponDataModel> data;
    private OnItemClickLister onItemClickLister;

    public void setOnItemClickLister(OnItemClickLister onItemClickLister) {
        this.onItemClickLister = onItemClickLister;
    }

    public void setData(List<CouponDataModel> data) {
        this.data = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView couponName;
        private final TextView expiration;
        private final TextView available;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.couponName = itemView.findViewById(R.id.coupon_name);
            this.expiration = itemView.findViewById(R.id.expiration_date);
            this.available = itemView.findViewById(R.id.available);

            itemView.findViewById(R.id.card_view_item).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){

//                        Log.d(GeneralVariable.TAG, data[pos].toString());
//                        if (onItemClickLister != null)
//                            onItemClickLister.onItemClick(view, pos, data[pos]);
                    }
                }
            });
        }

        public TextView getCouponName() {
            return couponName;
        }

        public TextView getExpiration() {
            return expiration;
        }

        public TextView getAvailable() {
            return available;
        }
    }

    public BoxAdapter(List<CouponDataModel> data) {
        this.data = data;
        if(this.data == null) {
            this.data = new ArrayList<>();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.digicu_box_recyclerview_item_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CouponDataModel couponDataModel = data.get(position);

        SimpleDateFormat dateFormat = new SimpleDateFormat("~ yyyy-MM-dd(E)");

        holder.getCouponName().setText(couponDataModel.getName());
        holder.getExpiration().setText(dateFormat.format(couponDataModel.getExpirationDate()));
        holder.getAvailable().setText(couponDataModel.isAvailable()?"사용가능":"사용불가");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}