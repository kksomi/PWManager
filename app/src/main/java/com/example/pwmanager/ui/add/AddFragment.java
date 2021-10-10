package com.example.pwmanager.ui.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pwmanager.Application;
import com.example.pwmanager.CipherUtils;
import com.example.pwmanager.R;
import com.example.pwmanager.StoreUtils;
import com.example.pwmanager.model.PasswordItem;
import com.google.android.material.textfield.TextInputEditText;

public class AddFragment extends Fragment {

    private AddViewModel viewModel;

    private TextInputEditText nameText;
    private TextInputEditText idText;
    private TextInputEditText urlText;
    private TextInputEditText pwText;
    private TextInputEditText memoText;
    private Button btn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(AddViewModel.class);
        View root = inflater.inflate(R.layout.activity_insert, container, false);
        nameText = root.findViewById(R.id.input_name);
        idText = root.findViewById(R.id.input_id);
        urlText = root.findViewById(R.id.input_url);
        pwText = root.findViewById(R.id.input_pw);
        memoText = root.findViewById(R.id.input_memo);
        btn = root.findViewById(R.id.input_button);

        btn.setOnClickListener(v -> {
            String name = nameText.getText().toString();
            String id = idText.getText().toString();
            String url = urlText.getText().toString();
            String memo = memoText.getText().toString();
            String pwPlainText = pwText.getText().toString();
            String pw = null;
            try {
                pw = CipherUtils.encrypt(pwPlainText, Application.getMasterKey());
            } catch (Exception e) {
                e.printStackTrace();
            }

            PasswordItem item = new PasswordItem();
            item.setName(name);
            item.setId(id);
            item.setUrl(url);
            item.setEncryptPassword(pw);
            item.setMemo(memo);

            saveItem(item);

            nameText.setText("");
            idText.setText("");
            urlText.setText("");
            pwText.setText("");
            memoText.setText("");

        });

        return root;
    }

    private void saveItem(PasswordItem item) {
        new StoreUtils(getContext()).addItem(item);
        Toast.makeText(getContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
    }
}