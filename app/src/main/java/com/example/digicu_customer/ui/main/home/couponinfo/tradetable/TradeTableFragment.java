package com.example.digicu_customer.ui.main.home.couponinfo.tradetable;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.ProposalDataModel;
import com.example.digicu_customer.data.dataset.TradeInfoDataModel;
import com.example.digicu_customer.data.remote.ApiUtils;
import com.example.digicu_customer.data.remote.DigicuCouponService;
import com.example.digicu_customer.general.CustomDate;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.digicu_customer.general.GeneralVariable.TAG;

public class TradeTableFragment extends Fragment {
    private TradeTableViewModel mViewModel;
    private RecyclerView tradeReqRecyclerView;
    private TradeTableAdapter tradeTableAdapter;
    private Button backBtn;
    private Button permitBtn;
    private TextView listEmptyBox;
    private TextView selectedItemEmptyBox;
    private TextView g1_couponName;
    private TextView g1_createDate;
    private TextView g1_expiration_date;
    private TextView g1_coupon_value;
    private TextView g2_couponName;
    private TextView g2_createDate;
    private TextView g2_expiration_date;
    private TextView g2_coupon_value;
    private ProposalDataModel selectedProposalData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        TextView title = ((AppCompatActivity)getActivity()).findViewById(R.id.main_title);
        title.setText(((AppCompatActivity)getActivity()).getString(R.string.trade_table));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(TradeTableViewModel.class);
        TradeInfoDataModel tradeInfoDataModel = (TradeInfoDataModel) getArguments().getSerializable("tradeInfoData");

        Log.e(TAG, "onActivityCreated: " + tradeInfoDataModel);

        mViewModel.setTradeInfoDataModel(tradeInfoDataModel);

        return inflater.inflate(R.layout.trade_table_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tradeReqRecyclerView = view.findViewById(R.id.trade_table_trade_req_coupon);
        permitBtn = view.findViewById(R.id.trade_table_permit_btn);
        backBtn = view.findViewById(R.id.trade_table_back_btn);
        listEmptyBox = view.findViewById(R.id.trade_table_empty_item);
        selectedItemEmptyBox = view.findViewById(R.id.trade_table_choose_empty_item);

        View temp = view.findViewById(R.id.trade_table_order);
        g1_couponName = temp.findViewById(R.id.coupon_name);
        g1_createDate = temp.findViewById(R.id.createDate);
        g1_expiration_date = temp.findViewById(R.id.expiration_date);
        g1_coupon_value = temp.findViewById(R.id.coupon_value);
        temp = view.findViewById(R.id.trade_table_requester);
        g2_couponName = temp.findViewById(R.id.coupon_name);
        g2_createDate = temp.findViewById(R.id.createDate);
        g2_expiration_date = temp.findViewById(R.id.expiration_date);
        g2_coupon_value = temp.findViewById(R.id.coupon_value);

        g1_couponName.setText(mViewModel.getTradeInfoDataModel().getCouponDataModel().getName());
        g1_createDate.setText(CustomDate.getDigicuDateFormat().format(mViewModel.getTradeInfoDataModel().getCouponDataModel().getCreatedAt()));
        g1_expiration_date.setText("만료일 : " + CustomDate.getDigicuDateFormat().format(mViewModel.getTradeInfoDataModel().getCouponDataModel().getExpirationDate()));
        g1_coupon_value.setText(mViewModel.getTradeInfoDataModel().getCouponDataModel().getValue() + "원");

        tradeReqRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tradeTableAdapter = new TradeTableAdapter();

        if (mViewModel.getTradeInfoDataModel() == null || mViewModel.getTradeInfoDataModel().getProposals() == null || mViewModel.getTradeInfoDataModel().getProposals().size() == 0) {
            tradeReqRecyclerView.setVisibility(View.GONE);
            listEmptyBox.setVisibility(View.VISIBLE);
        } else {
            tradeTableAdapter.setData(mViewModel.getTradeInfoDataModel().getProposals());
        }

        tradeReqRecyclerView.setAdapter(tradeTableAdapter);

        tradeTableAdapter.setOnItemClickLister(new TradeTableAdapter.OnItemClickLister() {
            @Override
            public void onItemClick(View v, int pos, ProposalDataModel data) {
                if (selectedItemEmptyBox.getVisibility() == View.VISIBLE) {
                    selectedItemEmptyBox.setVisibility(View.GONE);
                }

                g2_couponName.setText(data.getCouponDataModel().getName());
                g2_createDate.setText(CustomDate.getDigicuDateFormat().format(data.getCouponDataModel().getCreatedAt()));
                g2_expiration_date.setText("만료일 : " + CustomDate.getDigicuDateFormat().format(data.getCouponDataModel().getExpirationDate()));
                g2_coupon_value.setText(data.getCouponDataModel().getValue() + "원");

                selectedProposalData = data;
            }
        });

        permitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedProposalData == null) {
                    Snackbar snackbar = Snackbar.make(getView(), "교환할 쿠폰을 선택하세요!", Snackbar.LENGTH_SHORT);
                    snackbar.setAction("확인", v -> { snackbar.dismiss(); });
                    snackbar.show();
                    return;
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setMessage("정말 `" + selectedProposalData.getCouponDataModel().getName() + "`쿠폰과 교환하시겠습니까?\n교환 후 취소가 불가능합니다.")
                        .setTitle("교환알림")
                        .setNegativeButton("아니요", null)
                        .setPositiveButton("교환", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Todo : trade coupon!
                                DigicuCouponService digicuCouponService = ApiUtils.getDigicuCouponService();
                                digicuCouponService.postProposalAccept(selectedProposalData.getProposalId()).enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        Log.e(TAG, "onResponse: " + response.body());

                                        if (response.code() == 200) {
                                            Snackbar snackbar = Snackbar.make(getView(), "교환 완료", Snackbar.LENGTH_INDEFINITE);
                                            snackbar.setAction("확인", v -> { snackbar.dismiss(); });
                                            goBackNav();
                                            goBackNav();
                                        } else {
                                            Log.e(TAG, "onResponse: " + response.code());
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {
                                        Log.e(TAG, "onFailure: " + t.getMessage());
                                    }
                                });
                            }
                        })
                        .show();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBackNav();
            }
        });
    }

    private void goBackNav() {
        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.digicu_main_nav_host);
        NavController navController = navHostFragment.getNavController();
        navController.navigateUp();
    }

}