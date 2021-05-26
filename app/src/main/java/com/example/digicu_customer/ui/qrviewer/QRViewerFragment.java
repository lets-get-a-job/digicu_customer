package com.example.digicu_customer.ui.qrviewer;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.example.digicu_customer.R;
import com.example.digicu_customer.general.GeneralVariable;
import com.example.digicu_customer.util.crypto.DigicuCrypto;
import com.example.digicu_customer.util.qr_generator.QrGenerator;

import static com.example.digicu_customer.general.GeneralVariable.TAG;

public class QRViewerFragment extends DialogFragment {
    ImageView mImageView;
    QRViewerDialogListener mQrViewerDialogListener;

    public interface QRViewerDialogListener {
        void onFinishQRViewerDialog();
    }

    public void setmQrViewerDialogListener(QRViewerDialogListener mQrViewerDialogListener) {
        this.mQrViewerDialogListener = mQrViewerDialogListener;
    }

    public QRViewerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: ");

        return inflater.inflate(R.layout.fragment_q_r_viewer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");
        mImageView = view.findViewById(R.id.qr_viewer_img);
        QrGenerator qrGenerator = new QrGenerator();
        String data = getArguments().getString("transferDate");
        // Todo : encrypt transfer data
//        String cryptData = DigicuCrypto.

        qrGenerator.generateQRCode(data, 600, 600, mImageView);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog: ");
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        if (this.mQrViewerDialogListener != null) {
            mQrViewerDialogListener.onFinishQRViewerDialog();
        }
    }
}