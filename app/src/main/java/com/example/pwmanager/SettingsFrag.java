package com.example.pwmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsFrag extends Fragment {
    LinearLayout linearTheme;
    LinearLayout linearMaster;
    TextView theme;
    Button btnPop;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_settings, container, false);

        linearTheme = rootView.findViewById(R.id.linearTheme);
        linearTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingThemeActivity.class);
                startActivity(intent);
            }

        });

        linearMaster = rootView.findViewById(R.id.linearMaster);
        linearMaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingMasterActivity.class);
                startActivity(intent);
            }
        });

        linearMaster = rootView.findViewById(R.id.linearNotice);
        linearMaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingNoticeActivity.class);
                startActivity(intent);
            }
        });

//        theme = rootView.findViewById(R.id.theme);
//        theme.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), PopUpActivity.class);
//                startActivity(intent);          }
//        });

//        btnPop = rootView.findViewById(R.id.btnPop);
//        btnPop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), PopUpActivity.class);
//                startActivity(intent);
//            }
//        });

        return rootView;
    }

}
