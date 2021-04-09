package com.example.digicu_customer.ui.main.couponinfo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.CouponInfoDataModel;
import com.example.digicu_customer.data.dataset.RecordOfPurchaseDataModel;
import com.example.digicu_customer.general.GeneralVariable;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {
    public interface OnItemClickLister {
        void onItemClick(View v, int pos, RecordOfPurchaseDataModel data);
    }

    private List<RecordOfPurchaseDataModel> data;
    private OnItemClickLister onItemClickLister;


    public void setOnItemClickLister(OnItemClickLister onItemClickLister) {
        this.onItemClickLister = onItemClickLister;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView purchaseDate;
        private final TextView mileage_lv;
        private final TextView mileage;
        private final TextView totalPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.purchaseDate = itemView.findViewById(R.id.purchase_date);
            this.mileage = itemView.findViewById(R.id.record_mileage);
            this.mileage_lv = itemView.findViewById(R.id.mileage_label);
            this.totalPrice = itemView.findViewById(R.id.total_price);

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

        public TextView getPurchaseDate() {
            return purchaseDate;
        }

        public TextView getMileage_lv() {
            return mileage_lv;
        }

        public TextView getMileage() {
            return mileage;
        }

        public TextView getTotalPrice() {
            return totalPrice;
        }
    }

    public void setData(List<RecordOfPurchaseDataModel> data) {
        this.data = data;
    }

    public RecordAdapter(List<RecordOfPurchaseDataModel> data) {
        this.data = data;
        if(this.data == null) {
            this.data = new ArrayList<>();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.digicu_record_recyclerview_item_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecordOfPurchaseDataModel record = data.get(position);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd(E) HH:mm");

        holder.getPurchaseDate().setText(dateFormat.format(record.getPurchaseDate()));
        holder.getTotalPrice().setText(NumberFormat.getInstance(Locale.getDefault()).format(record.getTotalPrice())  + "원");
        if (record.getCouponInfoDataModel().getType() == CouponInfoDataModel.CouponType.STAMP) {
            holder.getMileage_lv().setText("적립 도장 ");
            holder.getMileage().setText(record.getProductDataModels().size() + "개");
        } else {
            holder.getMileage_lv().setText("적립 마일리지 ");
            holder.getMileage().setText(NumberFormat.getInstance(Locale.getDefault()).format(record.getTotalPrice() * record.getCouponInfoDataModel().getPercent()) + " points");
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
