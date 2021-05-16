package com.example.digicu_customer.ui.main.menu.log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.RecordOfPurchaseDataModel;
import com.example.digicu_customer.util.qr_generator.CustomDate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.ViewHolder> {
    public interface OnItemClickLister {
        void onItemClick(View v, int pos, RecordOfPurchaseDataModel data);
    }

    private List<RecordOfPurchaseDataModel> data;
    private OnItemClickLister onItemClickLister;

    public void setOnItemClickLister(OnItemClickLister onItemClickLister) {
        this.onItemClickLister = onItemClickLister;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView shopName;
        private final TextView date;
        private final TextView price;
        private final ImageButton imageButton;
        private final LinearLayout detailGroup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.shopName = itemView.findViewById(R.id.purchase_log_shop_name);
            this.date = itemView.findViewById(R.id.purchase_log_date);
            this.price = itemView.findViewById(R.id.purchase_log_price);
            this.imageButton = itemView.findViewById(R.id.purchase_log_expn_btn);
            this.detailGroup = itemView.findViewById(R.id.purchase_log_detail);

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){

                        if(detailGroup.getVisibility() == View.GONE) {
                            detailGroup.setVisibility(View.VISIBLE);
                            imageButton.setImageResource(R.drawable.ic_baseline_expand_less_24);
                        } else {
                            detailGroup.setVisibility(View.GONE);
                            imageButton.setImageResource(R.drawable.ic_baseline_expand_more_24);
                        }

                        if (onItemClickLister != null)
                            onItemClickLister.onItemClick(view, pos, data.get(pos));
                    }
                }
            });
        }

        public TextView getShopName() {
            return shopName;
        }

        public TextView getDate() {
            return date;
        }

        public TextView getPrice() {
            return price;
        }

        public ImageButton getImageButton() {
            return imageButton;
        }
    }

    public LogAdapter() {
        this.data = new ArrayList<>();
    }

    public LogAdapter(List<RecordOfPurchaseDataModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.digicu_purchase_log_recyclerview_item_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecordOfPurchaseDataModel recordOfPurchaseDataModel = data.get(position);

        SimpleDateFormat dateFormat = CustomDate.getDigicuDateFormatDetail();

        holder.getShopName().setText(recordOfPurchaseDataModel.getShopDataModel().getName());
        holder.getDate().setText(dateFormat.format(recordOfPurchaseDataModel.getDate()));
        holder.getPrice().setText(String.valueOf(recordOfPurchaseDataModel.getPrice()) + "원");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<RecordOfPurchaseDataModel> data) {
        this.data = data;
    }
}
