package com.example.digicu_customer.ui.main.home.savingcoupon.receipt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.ProductDataModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.ViewHolder> {
    private List<ProductDataModel> data;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView product_count;
        private final TextView product_name;
        private final TextView product_one_price;
        private final TextView product_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.product_count = itemView.findViewById(R.id.item_product_count);
            this.product_name = itemView.findViewById(R.id.item_product_name);
            this.product_price = itemView.findViewById(R.id.item_product_price);
            this.product_one_price = itemView.findViewById(R.id.item_product_one_price);
        }

        public TextView getProduct_count() {
            return product_count;
        }

        public TextView getProduct_name() {
            return product_name;
        }

        public TextView getProduct_price() {
            return product_price;
        }

        public TextView getProduct_one_price() {
            return product_one_price;
        }
    }

    public void setData(List<ProductDataModel> data) {
        this.data = data;
    }

    public ReceiptAdapter(List<ProductDataModel> data) {
        this.data = data;
        if(this.data == null) {
            this.data = new ArrayList<>();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.digicu_receipt_recyclerview_item_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductDataModel record = data.get(position);

        holder.getProduct_name().setText(record.getName());
        holder.getProduct_count().setText(NumberFormat.getInstance(Locale.getDefault()).format(record.getCount()));
        holder.getProduct_one_price().setText(NumberFormat.getInstance(Locale.getDefault()).format(record.getPrice()));
        holder.getProduct_price().setText(NumberFormat.getInstance(Locale.getDefault()).format(record.getCount() * record.getPrice()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
