package com.example.digicu_customer.ui.main.couponview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.CouponDataModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class CouponViewDialog extends BottomSheetDialogFragment {
    private BottomSheetItemClickListener mListener;
    private RecyclerView recyclerView;
    private TextView emptyView;
    private CouponViewDialogAdapter couponViewDialogAdapter;
    private CouponViewDialogModel couponViewDialogModel;

    public CouponViewDialog() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coupon_view_dialog, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        couponViewDialogModel = new ViewModelProvider(this).get(CouponViewDialogModel.class);
        couponViewDialogAdapter = new CouponViewDialogAdapter();
        recyclerView = view.findViewById(R.id.coupon_view_dialog_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(couponViewDialogAdapter);

        final Observer<List<CouponDataModel>> availObserver = new Observer<List<CouponDataModel>>() {
            @Override
            public void onChanged(List<CouponDataModel> couponDataModels) {
                couponViewDialogAdapter.setData(couponDataModels);
                couponViewDialogAdapter.notifyDataSetChanged();

                if (couponDataModels == null || couponDataModels.size() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                }
            }
        };

        couponViewDialogModel.getAvailableCouponModel().observe(getViewLifecycleOwner(), availObserver);

        emptyView = view.findViewById(R.id.coupon_view_dialog_empty);

        couponViewDialogAdapter.setOnItemClickLister(new CouponViewDialogAdapter.OnItemClickLister() {
            @Override
            public void onItemClick(View v, int pos, CouponDataModel data) {
                if (mListener != null) {
                    mListener.onItemClicked(data);
                }
                dismiss();
            }
        });
    }

    public interface BottomSheetItemClickListener {
        void onItemClicked(CouponDataModel couponDataModel);
    }

    public void setListener(BottomSheetItemClickListener mListener) {
        this.mListener = mListener;
    }
}