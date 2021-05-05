package com.example.digicu_customer.ui.main.search;

import android.os.Build;
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
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.ShopDataModel;
import com.example.digicu_customer.data.remote.ApiUtils;
import com.example.digicu_customer.data.remote.DigicuUserService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import static com.example.digicu_customer.general.GeneralVariable.TAG;

public class SearchFragment extends Fragment {
    ImageAdapter imageAdapter;
    RecyclerView recycler;
    TextInputLayout textInputLayout;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        TextView title = ((AppCompatActivity)getActivity()).findViewById(R.id.main_title);
        title.setText(getString(R.string.bottom_menu_title3));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        textInputLayout = view.findViewById(R.id.search_textfield_layout);

        imageAdapter = new ImageAdapter(getContext());
        recycler = view.findViewById(R.id.search_recyclerview);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setAdapter(imageAdapter);

        SearchViewModel searchViewModel = new ViewModelProvider(requireActivity()).get(SearchViewModel.class);

        final Observer<List<ShopDataModel>> listObserver = new Observer<List<ShopDataModel>>() {
            @Override
            public void onChanged(List<ShopDataModel> shopDataModels) {
                Log.d(TAG, "onChanged: " + shopDataModels.size());
                imageAdapter.setShopDataModels(shopDataModels);
                imageAdapter.notifyDataSetChanged();
            }
        };

        searchViewModel.getShopDataModelMutableLiveData().observe(requireActivity(), listObserver);

        textInputLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchViewModel.loadShopDataModel(textInputLayout.getEditText().getText().toString());
            }
        });

//        recycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//
//                if (!recyclerView.canScrollVertically(1)) {
//                    searchViewModel.nextPage();
//                }
//            }
//        });
    }
}