package com.example.digicu_customer.ui.main.search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.digicu_customer.R;
import com.example.digicu_customer.data.dataset.ShopDataModel;

import java.util.List;

import static com.example.digicu_customer.general.GeneralVariable.TAG;

public class SearchFragment extends Fragment {
    ImageAdapter imageAdapter;
    GridView gridView;

    public SearchFragment() {
        // Required empty public constructor
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

        imageAdapter = new ImageAdapter();
        gridView = view.findViewById(R.id.search_grid);
        gridView.setAdapter(imageAdapter);

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

        return view;
    }
}