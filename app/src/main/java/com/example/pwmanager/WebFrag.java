package com.example.pwmanager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WebFrag extends Fragment {

    private WebAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<String> list = null;


    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<String>();
        list.add("Google");
        list.add("Naver");
        list.add("Daum");
        list.add("Kakao");
        list.add("Dankook");
        list.add("Yahoo");

    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.activity_web, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.webView);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(recyclerView.getContext());

        recyclerView.setHasFixedSize(true);
        adapter = new WebAdapter(getContext(),list);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(adapter);


        //PasswordAdapter passAdapter = new PasswordAdapter();

        Log.e("Frag", "WebFrag");
        return rootView;

    }


}