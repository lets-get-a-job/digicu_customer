package com.example.digicu_customer.ui.main.home.couponinfo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.CouponDataModel;
import com.example.digicu_customer.data.dataset.CouponInfoDataModel;
import com.example.digicu_customer.data.dataset.ShopDataModel;
import com.example.digicu_customer.data.remote.ApiUtils;
import com.example.digicu_customer.data.remote.DigicuCouponService;
import com.example.digicu_customer.util.qr_generator.CustomDate;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

import static com.example.digicu_customer.general.GeneralVariable.TAG;

public class CouponInfoFragment extends Fragment {
    private CouponDataModel couponDataModel;
    private CouponInfoAdapter couponInfoAdapter;
    private GridView gridView;
    private TextView couponTitle;
    private TextView state_text;
    private ImageButton closeBtn;
    private TextView couponName;
    private TextView couponType;
    private TextView couponSavingCount;
    private TextView couponCreationDate;
    private TextView couponExpirationDate;
    private TextView couponInfoCouponState;
    private MaterialButton publishCoupon;
    private MaterialButton tradeCoupon;

    public CouponInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        closeBtn = ((AppCompatActivity)getActivity()).findViewById(R.id.main_close_btn);
        couponDataModel = (CouponDataModel) getArguments().getSerializable("couponData");

        Log.d(TAG, "onCreate: " + couponDataModel.toString());

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment navHostFragment = (NavHostFragment) ((AppCompatActivity)getActivity()).getSupportFragmentManager().findFragmentById(R.id.digicu_main_nav_host);
                NavController navController = navHostFragment.getNavController();
                navController.navigateUp();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        TextView title = ((AppCompatActivity)getActivity()).findViewById(R.id.main_title);
        title.setText(((AppCompatActivity)getActivity()).getString(R.string.coupon_info));
        closeBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coupon_info, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String couponName;

        couponTitle = view.findViewById(R.id.coupon_title);
        couponName = couponDataModel.getName().trim().toLowerCase();
        if (!couponName.endsWith("coupon") && !couponDataModel.getName().endsWith("쿠폰")) {
            couponTitle.setText(couponDataModel.getName() + " 쿠폰");
        }

        gridView = view.findViewById(R.id.coupon_stamp_gridview);
        couponInfoAdapter = new CouponInfoAdapter(getContext(), couponDataModel);
//        if((float)couponDataModel.getGoal()/(float)2 == 0.0)
        if (couponDataModel.getGoal() <= 15) {
            gridView.setNumColumns(5);
        } else if (couponDataModel.getGoal() >= 20) {
            gridView.setNumColumns(10);
        }
//        gridView.setNumColumns(couponDataModel.getGoal()/2);
        gridView.setAdapter(couponInfoAdapter);

        // bottom button
        publishCoupon = view.findViewById(R.id.coupon_info_publish_coupon_btn);
        tradeCoupon = view.findViewById(R.id.coupon_info_trade_coupon_btn);

        publishCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tradeCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setMessage("'" + couponDataModel.getName() + "' 쿠폰을 거래 등록을 하시겠습니까?")
                        .setTitle("거래등록확인")
                        .setPositiveButton("아니오", null)
                        .setNegativeButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                requestTradeCoupon(couponDataModel);
                            }
                        })
                        .show();
            }
        });

        //

        state_text = view.findViewById(R.id.state_text);
        if (couponDataModel.getState().equals("USED")) {
            state_text.setVisibility(View.VISIBLE);
            state_text.setText(view.getContext().getString(R.string.coupon_state_used));
            publishCoupon.setEnabled(false);
            tradeCoupon.setEnabled(false);
        } else if(couponDataModel.getState().equals("EXPIRED")) {
            state_text.setVisibility(View.VISIBLE);
            state_text.setText(view.getContext().getString(R.string.coupon_state_expiration));
            publishCoupon.setEnabled(false);
            tradeCoupon.setEnabled(false);
        } else if(couponDataModel.getState().equals("NORMAL") || couponDataModel.getState().equals("TRADING")) {
            publishCoupon.setEnabled(false);
            tradeCoupon.setEnabled(false);
        }

        this.couponName = view.findViewById(R.id.coupon_info_coupon_name);
        this.couponType = view.findViewById(R.id.coupon_info_coupon_type);
        this.couponSavingCount = view.findViewById(R.id.coupon_info_coupon_saving_count);
        this.couponCreationDate = view.findViewById(R.id.coupon_info_coupon_creation_date);
        this.couponExpirationDate = view.findViewById(R.id.coupon_info_coupon_expiration_date);
        this.couponInfoCouponState = view.findViewById(R.id.coupon_info_coupon_state);

        this.couponName.setText(couponDataModel.getName());
        this.couponType.setText(couponDataModel.getType().toString());
        this.couponSavingCount.setText(couponDataModel.getCount() + "/" + couponDataModel.getGoal());
        this.couponCreationDate.setText(CustomDate.getDigicuDateFormatDetail().format(couponDataModel.getCreatedAt()));
        this.couponExpirationDate.setText(CustomDate.getDigicuDateFormatDetail().format(couponDataModel.getExpirationDate()));
        this.couponInfoCouponState.setText(couponDataModel.getState());

        switch (couponDataModel.getState()){
            case "USED":
                this.couponInfoCouponState.setText("사용완료");
                this.couponInfoCouponState.setTextColor(getResources().getColor(R.color.digicu_black_color));
                break;
            case "DONE":
                this.couponInfoCouponState.setText("사용가능");
                this.couponInfoCouponState.setTextColor(getResources().getColor(R.color.digicu_base_primary_color));
                break;
            case "TRADING":
                this.couponInfoCouponState.setText("거래중");
                this.couponInfoCouponState.setTextColor(getResources().getColor(R.color.digicu_red));
                break;
            case "NORMAL":
                this.couponInfoCouponState.setText("적립중");
                break;
            default:
                break;
        }
    }

    public void requestTradeCoupon(CouponDataModel couponDataModel) {
        DigicuCouponService digicuCouponService = ApiUtils.getDigicuCouponService();

        // Todo update Coupon state to trading and update current coupon list
    }

    @Override
    public void onPause() {
        super.onPause();
        closeBtn.setVisibility(View.GONE);
    }
}