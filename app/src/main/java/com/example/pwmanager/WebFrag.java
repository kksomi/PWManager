package com.example.pwmanager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WebFrag extends Fragment {

    private final ArrayList<String> list = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        throw new AssertionError();


    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_web, container, false);

        RecyclerView recyclerView;
        recyclerView = rootView.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(recyclerView.getContext());

        recyclerView.setHasFixedSize(true);
        RecyclerAdapter adapter = new RecyclerAdapter(getContext(), null);
        recyclerView.setLayoutManager(layoutManager2);
        recyclerView.setAdapter(adapter);

        Log.e("Frag", "WebFrag");

        return rootView;

    }


}
