package com.example.pwmanager.ui.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pwmanager.Application;
import com.example.pwmanager.CipherUtils;
import com.example.pwmanager.R;

public class DetailFragment extends Fragment {

    private ListViewModel viewModel;

    private TextView nameText;
    private TextView idText;
    private TextView urlText;
    private TextView pwText;
    private TextView memoText;
    private String pw;
    private TextView dateText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(getActivity()).get(ListViewModel.class);

        //detail.xml 화면 불러오기 및 변수 설정
        View view = inflater.inflate(R.layout.detail, container, false);
        nameText = view.findViewById(R.id.view_name);
        idText = view.findViewById(R.id.view_id);
        urlText = view.findViewById(R.id.view_url);
        pwText = view.findViewById(R.id.view_pw);
        memoText = view.findViewById(R.id.view_memo);
        dateText = view.findViewById(R.id.view_userPush);

        TextView textView = (TextView) view.findViewById(R.id.view_pw);

        nameText.setEnabled(true);
        idText.setEnabled(true);
        urlText.setEnabled(true);
        pwText.setEnabled(true);
        memoText.setEnabled(true);
        dateText.setEnabled(true);

        //선택한 비밀번호 정보 불러오기
        viewModel.getSelectItem().observe(getViewLifecycleOwner(), item -> {
            if (item != null) {

                //CipherUtils 에서 암호화된 비밀번호를 복호화하여 pw에 저장
                try {
                    String plainPw = CipherUtils.decrypt(item.getEncryptPassword(), Application.getMasterKey());
                    pw = plainPw;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //불러온 비밀번호 정보를 변수에 저장
                nameText.setText(item.getName());
                idText.setText(item.getId());
                urlText.setText(item.getUrl());
                memoText.setText(item.getMemo());
                dateText.setText(item.getDate());
            }
        });

        //비밀번호 정보는 textView 클릭시 나타남
       textView.setOnClickListener(v -> pwText.setText(pw));

        return view;
    }

}