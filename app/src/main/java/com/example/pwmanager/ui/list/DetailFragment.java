package com.example.pwmanager.ui.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pwmanager.Application;
import com.example.pwmanager.CipherUtils;
import com.example.pwmanager.R;
import com.google.android.material.textfield.TextInputEditText;

public class DetailFragment extends Fragment {

    private ListViewModel viewModel;

    private TextInputEditText nameText;
    private TextInputEditText idText;
    private TextInputEditText urlText;
    private TextInputEditText pwText;
    private TextInputEditText memoText;
    private Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(getActivity()).get(ListViewModel.class);

        View root = inflater.inflate(R.layout.activity_insert, container, false);
        nameText = root.findViewById(R.id.input_name);
        idText = root.findViewById(R.id.input_id);
        urlText = root.findViewById(R.id.input_url);
        pwText = root.findViewById(R.id.input_pw);
        memoText = root.findViewById(R.id.input_memo);
        btn = root.findViewById(R.id.input_button);
        btn.setVisibility(View.GONE);

        nameText.setEnabled(false);
        idText.setEnabled(false);
        urlText.setEnabled(false);
        pwText.setEnabled(false);
        memoText.setEnabled(false);

        viewModel.getSelectItem().observe(getViewLifecycleOwner(), item -> {
            if(item!=null) {
                nameText.setText(item.getName());
                idText.setText(item.getId());
                urlText.setText(item.getUrl());
                pwText.setText(item.getEncryptPassword());
                memoText.setText(item.getMemo());

                try {
                    String plainPw = CipherUtils.decrypt(item.getEncryptPassword(), Application.getMasterKey());
                    Toast.makeText(getContext(), "비밀번호="+plainPw, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return root;
    }
}