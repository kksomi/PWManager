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

public class PasswordFrag extends Fragment {

    private RecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<String> list = null;



    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<String>();
        list.add("password");
        list.add("qlalfqjsgh");
        list.add("12345678");
        list.add("secretNumber");
        list.add("pass1234");
        list.add("qwerasdf");

        }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.activity_password, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(recyclerView.getContext());

        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter(getContext(),list);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(adapter);


        Log.e("Frag", "PasswordFrag");
        return rootView;

    }


}
