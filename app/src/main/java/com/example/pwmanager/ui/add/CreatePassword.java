package com.example.pwmanager.ui.add;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.pwmanager.R;

import java.util.Random;

public class CreatePassword extends DialogFragment {
    OnMyDialogResult mDialogResult;
    private Fragment fragment;
    private TextView textPassword;
    private int leftLimit = 48;
    private int rightLimit = 122;
    private int targetStringLength;
    Random rnd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE,R.style.Widget_MaterialComponents_MaterialCalendar_Fullscreen);
        isCancelable();

    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.create_password, container, false);
        rnd = new Random();

        Button create = (Button) root.findViewById(R.id.pw_create);
        Button cancel = (Button) root.findViewById(R.id.cancel);
        Button save = (Button) root.findViewById(R.id.save_pw);
        textPassword = (TextView) root.findViewById(R.id.createdpw);

        create.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                targetStringLength = 10;
                String generatedString = rnd.ints(leftLimit, rightLimit+1)
                        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                        .limit(targetStringLength)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString();

                String randStr = new RandomStringBuilder()
                        .putLimitedChar(RandomStringBuilder.ALPHABET)
                        .putLimitedChar(RandomStringBuilder.SPECIAL)
                        .putLimitedChar(RandomStringBuilder.NUMBER)
                        .setLength(targetStringLength)
                        .build();

                textPassword.setText(String.valueOf(randStr));
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pw = textPassword.getText().toString();
                mDialogResult.finish(pw);
                dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return root;
    }

    public void setDialogResult(OnMyDialogResult dialogResult) {
        mDialogResult = dialogResult;
    }

    public interface OnMyDialogResult {
        void finish(String result);
    }

}
