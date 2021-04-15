package com.example.digicu_customer.util.qr_generator;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.hardware.camera2.CameraCharacteristics;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.digicu_customer.ui.main.home.HomeFragment;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.qrcode.QRCodeWriter;

public class QrGenerator {

    Activity mActivity;
    Fragment mFragment;

    public QrGenerator(Activity activity) {
        this.mActivity = activity;
    }

    public QrGenerator(Activity mActivity, Fragment mFragment) {
        this.mActivity = mActivity;
        this.mFragment = mFragment;
    }

    public void startQRCode() {
        IntentIntegrator integrator = new IntentIntegrator(mActivity);
        // 전면카메라 세팅
        integrator.setCameraId(CameraCharacteristics.LENS_FACING_FRONT);
        integrator.forSupportFragment(mFragment).initiateScan();
    }

    public void generateQRCode(String contents, int width, int height, ImageView imageView) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        try {
            Bitmap bitmap = toBitmap(qrCodeWriter.encode(contents, BarcodeFormat.QR_CODE, width, height));
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap toBitmap(BitMatrix matrix) {
        int height = matrix.getHeight();
        int width = matrix.getWidth();

        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bmp.setPixel(x, y, matrix.get(x, y) ? Color.BLACK : Color.WHITE);
            }
        }

        return bmp;
    }
}
