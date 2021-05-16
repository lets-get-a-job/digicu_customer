package com.example.digicu_customer.ui.main.home.savingcoupon.receipt;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.RecordOfPurchaseDataModel;
import com.example.digicu_customer.general.GeneralVariable;

import java.text.SimpleDateFormat;

public class ReceiptFragment extends Fragment {
    ReceiptViewModel receiptViewModel;
    ReceiptAdapter receiptAdapter;
    RecyclerView recyclerView;

    public ReceiptFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(GeneralVariable.TAG, "onResume: Receipt onResume");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        receiptViewModel = new ViewModelProvider(this).get(ReceiptViewModel.class);
        receiptAdapter = new ReceiptAdapter(null);

        final Observer<RecordOfPurchaseDataModel> recordOfPurchaseDataModelObserver = new Observer<RecordOfPurchaseDataModel>() {
            @Override
            public void onChanged(RecordOfPurchaseDataModel recordOfPurchaseDataModel) {
                // set data
                receiptAdapter.setData(recordOfPurchaseDataModel.getProductDataModels());
                receiptAdapter.notifyDataSetChanged();
            }
        };
        receiptViewModel.getRecord().observe(requireActivity(), recordOfPurchaseDataModelObserver);
        receiptViewModel.getRecord().setValue((RecordOfPurchaseDataModel) getArguments().getSerializable("record"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_receipt, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        RecordOfPurchaseDataModel recordOfPurchaseDataModel = (RecordOfPurchaseDataModel) getArguments().getSerializable("record");

        recyclerView = view.findViewById(R.id.receipt_products_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(receiptAdapter);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd(E) HH:mm");

//        ((TextView)view.findViewById(R.id.receipt_date)).setText(dateFormat.format(recordOfPurchaseDataModel.getPurchaseDate()));
////        if (recordOfPurchaseDataModel.getCouponInfoDataModel().getType() == CouponInfoDataModel.CouponType.STAMP) {
//            // Todo modify code after establish coupon strategy
//            ((TextView)view.findViewById(R.id.receipt_mileage)).setText("적립도장 : " + recordOfPurchaseDataModel.getStampCnt() + "개");
////        } else {
////            ((TextView)view.findViewById(R.id.receipt_mileage)).setText("적립 포인트 : " + NumberFormat.getInstance(Locale.getDefault()).format(recordOfPurchaseDataModel.getMileage()) + "points");
////        }
//        ((TextView)view.findViewById(R.id.receipt_price)).setText(NumberFormat.getInstance(Locale.getDefault()).format(recordOfPurchaseDataModel.getTotalPrice()) + "원");
    }
}