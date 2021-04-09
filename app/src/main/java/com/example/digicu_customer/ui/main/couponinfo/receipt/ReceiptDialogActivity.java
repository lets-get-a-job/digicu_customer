package com.example.digicu_customer.ui.main.couponinfo.receipt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.example.digicu_customer.R;
import com.example.digicu_customer.general.GeneralVariable;

public class ReceiptDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_receipt_dialog);

        String a = getIntent().getStringExtra("sample");
        Log.d(GeneralVariable.TAG, "onCreate: " + a);
    }
}