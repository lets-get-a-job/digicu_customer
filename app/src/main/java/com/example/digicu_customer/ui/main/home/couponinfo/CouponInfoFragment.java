package com.example.digicu_customer.ui.main.home.couponinfo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.CouponDataModel;
import com.example.digicu_customer.data.dataset.CouponTradeRegisterDataModel;
import com.example.digicu_customer.data.dataset.ProposalDataModel;
import com.example.digicu_customer.data.dataset.ProposalRequestDataModel;
import com.example.digicu_customer.data.dataset.TradeDataModel;
import com.example.digicu_customer.data.dataset.TradeInfoDataModel;
import com.example.digicu_customer.data.remote.ApiUtils;
import com.example.digicu_customer.data.remote.DigicuCouponService;
import com.example.digicu_customer.data.remote.RetrofitClient;
import com.example.digicu_customer.ui.main.couponview.CouponViewDialog;
import com.example.digicu_customer.ui.qrviewer.QRViewerFragment;
import com.example.digicu_customer.general.CustomDate;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.digicu_customer.general.GeneralVariable.TAG;

public class CouponInfoFragment extends Fragment implements View.OnClickListener {
    private CouponDataModel couponDataModel;
    private TradeInfoDataModel tradeInfoDataModel;
    private ProposalDataModel userProposalDataModel;

    private CouponInfoAdapter couponInfoAdapter;
    private GridView gridView;
    private GridLayout coupon_info_button_group;
    private GridLayout coupon_info_trade_button_group;
    private TextView couponTitle;
    private TextView state_text;
    private ImageButton closeBtn;
    private TextView couponName;
    private TextView couponType;
    private TextView couponSavingCount;
    private TextView couponCreationDate;
    private TextView couponExpirationDate;
    private TextView couponInfoCouponState;
    private TextView couponValue;
    private MaterialButton publishCoupon;
    private MaterialButton tradeCoupon;
    private MaterialButton tradeReq;
    private MaterialButton tradeBack;

    private MaterialCardView notificationCard;
    private TextView tradeRequestCount;

    public CouponInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        closeBtn = getActivity().findViewById(R.id.main_close_btn);
        notificationCard = getActivity().findViewById(R.id.main_trade_request);
        tradeRequestCount = getActivity().findViewById(R.id.coupon_trade_request_count);
        couponDataModel = (CouponDataModel) getArguments().getSerializable("couponData");

        notificationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.digicu_main_nav_host);
                NavController navController = navHostFragment.getNavController();
                Bundle bundle = new Bundle();
                bundle.putSerializable("tradeInfoData", tradeInfoDataModel);

                if (navController.getCurrentDestination().getId() == R.id.couponInfoFragment2) {
                    navController.navigate(R.id.action_couponInfoFragment2_to_tradeTableFragment3, bundle);
                } else if (navController.getCurrentDestination().getId() == R.id.couponInfoFragment3) {
                    navController.navigate(R.id.action_couponInfoFragment3_to_tradeTableFragment2, bundle);
                }


            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBackNav();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        TextView title = ((AppCompatActivity)getActivity()).findViewById(R.id.main_title);
        title.setText(((AppCompatActivity)getActivity()).getString(R.string.coupon_info));
        closeBtn.setVisibility(View.VISIBLE);

        updateUI(couponDataModel);
    }

    private void updateUI(CouponDataModel couponDataModel) {
        // todo : switch tradeReq name(교환요청 or 거래 취소) if user is owner or trade requester

        if (couponDataModel.getState().equals("USED")) {
            state_text.setVisibility(View.VISIBLE);
            state_text.setText(getContext().getString(R.string.coupon_state_used));
            publishCoupon.setEnabled(false);
            tradeCoupon.setEnabled(false);
        } else if(couponDataModel.getState().equals("EXPIRED")) {
            state_text.setVisibility(View.VISIBLE);
            state_text.setText(getContext().getString(R.string.coupon_state_expiration));
            publishCoupon.setEnabled(false);
            tradeCoupon.setEnabled(false);
        } else if(couponDataModel.getState().equals("NORMAL") || couponDataModel.getState().equals("TRADING") || couponDataModel.getState().equals("TRADING_REQ")) {
            publishCoupon.setEnabled(false);
            tradeCoupon.setEnabled(false);
        }

        this.couponName.setText(couponDataModel.getName());
        this.couponType.setText(couponDataModel.getType().toString());
        this.couponSavingCount.setText(couponDataModel.getCount() + "/" + couponDataModel.getGoal());
        this.couponCreationDate.setText(CustomDate.getDigicuDateFormatDetail().format(couponDataModel.getCreatedAt()));
        this.couponExpirationDate.setText(CustomDate.getDigicuDateFormatDetail().format(couponDataModel.getExpirationDate()));
        this.couponInfoCouponState.setText(couponDataModel.getState());
        this.couponValue.setText(couponDataModel.getValue() + "원");

        switch (couponDataModel.getState()){
            case "USED":
                this.couponInfoCouponState.setText("사용완료");
                this.couponInfoCouponState.setTextColor(getResources().getColor(R.color.digicu_dark_gray_color));
                break;
            case "DONE":
                this.couponInfoCouponState.setText("사용가능");
                this.couponInfoCouponState.setTextColor(getResources().getColor(R.color.digicu_base_primary_color));
                break;
            case "TRADING":
                this.couponInfoCouponState.setText("거래중");
                this.couponInfoCouponState.setTextColor(getResources().getColor(R.color.digicu_red));
                break;
            case "TRADING_REQ":
                this.couponInfoCouponState.setText("거래 요청 중");
                this.couponInfoCouponState.setTextColor(getResources().getColor(R.color.purple_200));
                tradeReq.setText(getString(R.string.trade_request_cancel));
                break;
            case "NORMAL":
                this.couponInfoCouponState.setText("적립중");
                break;
            default:
                break;
        }

        if (couponDataModel.getState().toLowerCase().equals(CouponDataModel.coupon_state.trading.name()) && couponDataModel.getOwner().equals(RetrofitClient.getSocialUserDataModel().getPhone())) {
            notificationCard.setVisibility(View.VISIBLE);
            tradeReq.setText(getString(R.string.trade_cancel));
        }

        String viewType = getArguments().getString("viewType");
        if(viewType != null && viewType.equals("trade_request")) {
            // when same coupon owner and viewer

            coupon_info_button_group.setVisibility(View.GONE);
            coupon_info_trade_button_group.setVisibility(View.VISIBLE);

//            if(couponDataModel.getState().toLowerCase().equals(CouponDataModel.coupon_state.trading_req)) {
//                tradeReq.setText(getString(R.string.trade_request_cancel));
//            }
        } else {
            Log.d(TAG, "updateUI: " + couponDataModel.getState());

            if (couponDataModel.getState().equals(CouponDataModel.coupon_state.trading.name().toUpperCase())) {
                tradeCoupon.setEnabled(true);
                tradeCoupon.setText(getString(R.string.trade_cancel));
            } else if (couponDataModel.getState().equals(CouponDataModel.coupon_state.trading_req.name().toUpperCase())) {
                tradeCoupon.setEnabled(true);
                tradeCoupon.setText(getString(R.string.trade_request_cancel));
            }
        }

        // update trade request count
        if (couponDataModel.getTradeId() != null) {
            Log.e(TAG, "updateUI: ----------------------------------------------------------------");
            DigicuCouponService digicuCouponService = ApiUtils.getDigicuCouponService();
            digicuCouponService.getCouponTradeInfo(couponDataModel.getTradeId()).enqueue(new Callback<TradeInfoDataModel>() {
                @Override
                public void onResponse(Call<TradeInfoDataModel> call, Response<TradeInfoDataModel> response) {
                    Log.e(TAG, "onResponse: " + response.body());
                    if (response.code() == 200) {
                        tradeInfoDataModel = response.body();
                        if (tradeInfoDataModel.getProposals() != null) {
                            // coupon owner only
                            tradeRequestCount.setText(String.valueOf(tradeInfoDataModel.getProposals().size()));

                            // coupon trader only
                            for (ProposalDataModel proposalDataModel: tradeInfoDataModel.getProposals()) {
                                Log.d(TAG, "onResponse: " + proposalDataModel);
                                if (!proposalDataModel.getOwner().equals(couponDataModel.getOwner())) {
                                    userProposalDataModel = proposalDataModel;
                                    tradeReq.setText(getString(R.string.trade_request_cancel));
                                }
                            }
                        }
                    } else {
                        Log.e(TAG, "onResponse: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<TradeInfoDataModel> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getMessage());
                }
            });

        }
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

        coupon_info_trade_button_group = view.findViewById(R.id.coupon_info_trade_button_group);
        coupon_info_button_group = view.findViewById(R.id.coupon_info_button_group);

        couponTitle = view.findViewById(R.id.coupon_title);
        couponName = couponDataModel.getName().trim().toLowerCase();
        if (!couponName.endsWith("coupon") && !couponDataModel.getName().endsWith("쿠폰")) {
            couponTitle.setText(couponDataModel.getName() + " 쿠폰");
        } else {
            couponTitle.setText(couponDataModel.getName());
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
        publishCoupon.setOnClickListener(this);
        tradeCoupon = view.findViewById(R.id.coupon_info_trade_coupon_btn);
        tradeCoupon.setOnClickListener(this);
        tradeReq = view.findViewById(R.id.coupon_info_trade_req_btn);
        tradeReq.setOnClickListener(this);
        tradeBack = view.findViewById(R.id.coupon_info_trade_back);
        tradeBack.setOnClickListener(this);

        state_text = view.findViewById(R.id.state_text);

        this.couponName = view.findViewById(R.id.coupon_info_coupon_name);
        this.couponType = view.findViewById(R.id.coupon_info_coupon_type);
        this.couponSavingCount = view.findViewById(R.id.coupon_info_coupon_saving_count);
        this.couponCreationDate = view.findViewById(R.id.coupon_info_coupon_creation_date);
        this.couponExpirationDate = view.findViewById(R.id.coupon_info_coupon_expiration_date);
        this.couponInfoCouponState = view.findViewById(R.id.coupon_info_coupon_state);
        this.couponValue = view.findViewById(R.id.coupon_info_coupon_value);
    }

    @Override
    public void onPause() {
        super.onPause();
        closeBtn.setVisibility(View.GONE);
        notificationCard.setVisibility(View.GONE);
    }

    public void onFinishQRViewerDialog() {
        DigicuCouponService digicuCouponService = ApiUtils.getDigicuCouponService();

        digicuCouponService.getCouponDataByID(couponDataModel.getCouponKey()).enqueue(new Callback<CouponDataModel>() {
            @Override
            public void onResponse(Call<CouponDataModel> call, Response<CouponDataModel> response) {
                if (response.code() == 200) {
                    couponDataModel = response.body();
                    updateUI(couponDataModel);
                } else {
                    Log.d(TAG, "onResponse: " +  response.code());
                }
            }

            @Override
            public void onFailure(Call<CouponDataModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.coupon_info_publish_coupon_btn:
                FragmentManager fragmentManager = ((AppCompatActivity)getActivity()).getSupportFragmentManager();
                QRViewerFragment qrViewerFragment = new QRViewerFragment();
                Bundle bundle = new Bundle();
                bundle.putString("transferDate", String.valueOf(couponDataModel.getCouponKey()));

                qrViewerFragment.setmQrViewerDialogListener(CouponInfoFragment.this::onFinishQRViewerDialog);
                qrViewerFragment.setArguments(bundle);

                qrViewerFragment.show(fragmentManager, "dialog");
                break;
            case R.id.coupon_info_trade_coupon_btn:
                if (tradeCoupon.getText().equals(getString(R.string.trade_cancel))) {
                    deleteTradeCoupon(couponDataModel);
                } else if (tradeCoupon.getText().equals(getString(R.string.trade_request_cancel))) {
                    Log.e(TAG, "onClick: trade request cancel");

                    deleteProposalRequestWithCouponModel(couponDataModel);

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    builder.setMessage("'" + couponDataModel.getName() + "' 쿠폰을 거래 등록을 하시겠습니까?")
                            .setTitle("거래등록확인")
                            .setPositiveButton("아니오", null)
                            .setNegativeButton("예", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    tradeCoupon.setEnabled(false);
                                    registerTradeCoupon(couponDataModel);
                                }
                            })
                            .show();
                }
                break;
            case R.id.coupon_info_trade_req_btn:
                if (tradeReq.getText().equals(getString(R.string.trade_request))) {
                    Log.d(TAG, "onClick: req");
                    CouponViewDialog couponViewDialog = new CouponViewDialog();

                    couponViewDialog.setListener(new CouponViewDialog.BottomSheetItemClickListener() {
                        @Override
                        public void onItemClicked(CouponDataModel couponDataModel) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                            builder.setMessage("`" + couponDataModel.getName() + "`으로 교환 신청하시겠습니까?")
                                    .setTitle("알림")
                                    .setPositiveButton("아니오", null)
                                    .setNegativeButton("예", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            DigicuCouponService digicuCouponService = ApiUtils.getDigicuCouponService();
                                            digicuCouponService.postProposalRequest(new ProposalRequestDataModel(tradeInfoDataModel.getTradeId(), couponDataModel.getCouponKey())).enqueue(new Callback<ProposalDataModel>() {
                                                @Override
                                                public void onResponse(Call<ProposalDataModel> call, Response<ProposalDataModel> response) {
                                                    if (response.code() == 200) {
                                                        Snackbar snackbar = Snackbar.make(requireView(), "거래 요청 되었습니다.", Snackbar.LENGTH_INDEFINITE);
                                                        snackbar.setTextColor(getResources().getColor(R.color.white));
                                                        snackbar.setAction("확인", new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View view) {
                                                                snackbar.dismiss();
                                                            }
                                                        });
                                                        goBackNav();
                                                        snackbar.show();
                                                    } else {
                                                        Log.e(TAG, "onResponse: " + response.code());
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<ProposalDataModel> call, Throwable t) {
                                                    Log.e(TAG, "onFailure: " + t.getMessage());
                                                }
                                            });
                                        }
                                    })
                                    .show();


                        }
                    });

                    couponViewDialog.show(requireFragmentManager(), "coupon bottomUp sheet");

                } else if (tradeReq.getText().equals(getString(R.string.trade_request_cancel))) {
                    Log.d(TAG, "onClick: me! cancel");
                    deleteProposalRequestWithCouponModel(couponDataModel);
                } else {
                    // cancel trading coupon
                    deleteTradeCoupon(couponDataModel);
                }

                break;
            case R.id.coupon_info_trade_back:
                goBackNav();
                break;
        }
    }

    private void deleteProposalRequestWithCouponModel(CouponDataModel couponDataModel) {
        DigicuCouponService digicuCouponService = ApiUtils.getDigicuCouponService();
        digicuCouponService.getProposalRequestWithCouponId(couponDataModel.getCouponKey()).enqueue(new Callback<List<ProposalDataModel>>() {
            @Override
            public void onResponse(Call<List<ProposalDataModel>> call, Response<List<ProposalDataModel>> response) {
                Log.e(TAG, "onResponse: " + response.body());

                if (response.code() == 200) {
                    ProposalDataModel proposalDataModel = response.body().get(0);
                    if (proposalDataModel != null) {
                        deleteProposalRequest(proposalDataModel);
                    }
                } else {
                    Log.e(TAG, "onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<ProposalDataModel>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void deleteProposalRequest(ProposalDataModel proposalDataModel) {
        if (proposalDataModel == null || proposalDataModel.getProposalId() == null) {
            Snackbar.make(getView(), "교환 요청 취소 실패", Snackbar.LENGTH_LONG).show();
            return;
        }

        DigicuCouponService digicuCouponService = ApiUtils.getDigicuCouponService();

        digicuCouponService.deleteProposalRequest(proposalDataModel.getProposalId()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 204) {
                    Snackbar snackbar = Snackbar.make(getView(), "요청 취소 완료", Snackbar.LENGTH_SHORT);
                    snackbar.setAction("확인", v -> {snackbar.dismiss();});
                    snackbar.show();
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

    private void goBackNav() {
        NavHostFragment navHostFragment = (NavHostFragment) ((AppCompatActivity)getActivity()).getSupportFragmentManager().findFragmentById(R.id.digicu_main_nav_host);
        NavController navController = navHostFragment.getNavController();
        navController.navigateUp();
    }

    private void deleteTradeCoupon(CouponDataModel couponDataModel) {
        // must need coupon trade info
        if (couponDataModel.getTradeId() == null) {
            return;
        }

        DigicuCouponService digicuCouponService = ApiUtils.getDigicuCouponService();
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //todo : remove trade coupon info

                digicuCouponService.deleteCouponTrade(couponDataModel.getTradeId()).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d(TAG, "onResponse delete trade info : " + response.body());

                        if (response.code() == 204) {
                            goBackNav();
                            Snackbar snackbar = Snackbar.make(requireView(), couponDataModel.getName() + "쿠폰 교환 등록이 취소되었습니다.", Snackbar.LENGTH_INDEFINITE);
                            snackbar.setTextColor(getResources().getColor(R.color.white));
                            snackbar.setAction("확인", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    snackbar.dismiss();
                                }
                            });

                        } else {
                            Log.e(TAG, "onResponse delete trade info  : " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e(TAG, "onFailure delete trade info : " + t.getMessage());
                    }
                });
            }
        };


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setMessage(getContext().getString(R.string.tradeCancelPerm))
                .setTitle("알림")
                .setPositiveButton("아니오", null)
                .setNegativeButton("예", onClickListener)
                .show();
    }

    public void registerTradeCoupon(CouponDataModel couponDataModel) {
        DigicuCouponService digicuCouponService = ApiUtils.getDigicuCouponService();

        // Todo update Coupon state to trading and update current coupon list
        CouponTradeRegisterDataModel couponTradeRegisterDataModel = new CouponTradeRegisterDataModel(couponDataModel.getCouponKey(), 0);

        digicuCouponService.postCouponTrade(couponTradeRegisterDataModel).enqueue(new Callback<TradeInfoDataModel>() {
            @Override
            public void onResponse(Call<TradeInfoDataModel> call, Response<TradeInfoDataModel> response) {
                if (response.code() == 200) {
                    Log.d(TAG, "onResponse: " + response.body());
                    CouponInfoFragment.this.couponDataModel = response.body().getCouponDataModel();
                    updateUI(CouponInfoFragment.this.couponDataModel);
                } else {
                    tradeCoupon.setEnabled(true);
                    Log.d(TAG, "onResponse couponinfo trade register : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TradeInfoDataModel> call, Throwable t) {
                tradeCoupon.setEnabled(true);
                Log.d(TAG, "onFailure couponinfo trade register: " + t.getMessage());
            }
        });
    }
}