package com.example.digicu_customer.ui.main.menu.log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.RecordOfPurchaseDataModel;

import java.util.List;

public class LogFragment extends Fragment {
    private AppCompatActivity mainActivity;
    private LogViewModel mViewModel;
    private LogAdapter mLogAdapter;
    private RecyclerView recyclerView;
    private ImageButton closeBtn;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.log_fragment, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLogAdapter = new LogAdapter();
        closeBtn = ((AppCompatActivity)getActivity()).findViewById(R.id.main_close_btn);

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.purchase_log_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mLogAdapter);

        closeBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStart() {
        super.onStart();
        mainActivity = (AppCompatActivity) getActivity();
        ((TextView)mainActivity.findViewById(R.id.main_title)).setText(getString(R.string.payment_log));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LogViewModel.class);

        final Observer<List<RecordOfPurchaseDataModel>> observer = new Observer<List<RecordOfPurchaseDataModel>>() {
            @Override
            public void onChanged(List<RecordOfPurchaseDataModel> recordOfPurchaseDataModels) {
                mLogAdapter.setData(recordOfPurchaseDataModels);
                mLogAdapter.notifyDataSetChanged();
            }
        };

        mViewModel.getRecordsModel().observe(getViewLifecycleOwner(), observer);
    }

    @Override
    public void onPause() {
        super.onPause();
        closeBtn.setVisibility(View.GONE);
    }
}