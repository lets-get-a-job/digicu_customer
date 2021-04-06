package com.example.digicu_customer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.digicu_customer.adapter.MenuAdapter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.ArrayList;

public class MenuFragment extends Fragment implements AdapterView.OnItemClickListener {
    ListView mListView;
    MenuAdapter menuAdapter;
    String dataStrings[];
    GoogleSignInAccount mSignInAccount;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch (position) {
                case 0:
                    Log.d(GeneralVariable.TAG, "onItemClick: ");
                    break;
                case 1:
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    mGoogleSignInClient.signOut();
                    startActivity(intent);
                    break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        dataStrings = new String[]{"회원정보", "로그아웃"};
        ArrayList<String> list = new ArrayList<>();

        for (String data : dataStrings)
            list.add(data);

        mListView = view.findViewById(R.id.digicu_fragment_menu_listview);
        menuAdapter = new MenuAdapter(getContext(), list);
        mListView.setAdapter(menuAdapter);
        mListView.setOnItemClickListener(this);

        return view;
    }
}