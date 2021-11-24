package com.example.pwmanager.ui.add;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListPopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.pwmanager.R;
import com.example.pwmanager.ui.list.OnDialogListener;

import java.lang.reflect.Field;
import java.util.Random;

public class CreatePassword extends DialogFragment implements CompoundButton.OnCheckedChangeListener{
    OnMyDialogResult mDialogResult;
    private Fragment fragment;
    private OnDialogListener listener;
    private TextView textPassword;
    private int targetStringLength;
    private String[] lengths = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
            "12", "13", "14", "15", "16", "17", "18", "19", "20"};
    private boolean eng_s = true;
    private boolean eng_l = true;
    private boolean num = true;
    private boolean spe = true;
    Random rnd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE,R.style.Widget_MaterialComponents_MaterialCalendar_Fullscreen);
        isCancelable();

    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.create_password, container, false);
        rnd = new Random(System.currentTimeMillis());

        Button create = (Button) root.findViewById(R.id.pw_create);
        Button cancel = (Button) root.findViewById(R.id.cancel);
        Button save = (Button) root.findViewById(R.id.save_pw);

        textPassword = (TextView) root.findViewById(R.id.createdpw);
        Spinner spinner_length = (Spinner) root.findViewById(R.id.spinner_length);

        CheckBox ENG_S = (CheckBox) root.findViewById(R.id.ENG_S);
        ENG_S.setOnCheckedChangeListener(this);
        CheckBox ENG_L = (CheckBox) root.findViewById(R.id.ENG_L);
        ENG_L.setOnCheckedChangeListener(this);
        CheckBox NUM = (CheckBox) root.findViewById(R.id.NUM);
        NUM.setOnCheckedChangeListener(this);
        CheckBox SPE = (CheckBox) root.findViewById(R.id.SPE);
        SPE.setOnCheckedChangeListener(this);

        //spinner 높이 설정
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            ListPopupWindow window = (ListPopupWindow)popup.get(spinner_length);
            window.setHeight(100); //pixel
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> lenAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,
                lengths);
        lenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_length.setAdapter(lenAdapter);
        spinner_length.setSelection(8);

        //spinner 값 선택 시 targetStringLength로 값 설정
        spinner_length.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                targetStringLength = Integer.parseInt(lengths[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //생성 버튼 클릭 이벤트
        create.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                RandomStringBuilder randomStringBuilder= new RandomStringBuilder();
                randomStringBuilder.setLength(targetStringLength);
                String randStr="";

                if(eng_s) {
                    randomStringBuilder.putLimitedChar(RandomStringBuilder.ALPHABET_LOWER_CASE);
                }
                else {
                    randomStringBuilder.putExcludedChar(RandomStringBuilder.ALPHABET_LOWER_CASE);
                }
                if(eng_l) {
                    randomStringBuilder.putLimitedChar(RandomStringBuilder.ALPHABET_UPPER_CASE);
                }
                else {
                    randomStringBuilder.putExcludedChar(RandomStringBuilder.ALPHABET_UPPER_CASE);
                }
                if(num) {
                    randomStringBuilder.putLimitedChar(RandomStringBuilder.NUMBER);
                }
                else {
                    randomStringBuilder.putExcludedChar(RandomStringBuilder.NUMBER);
                }
                if(spe) {
                    randomStringBuilder.putLimitedChar(RandomStringBuilder.SPECIAL);
                }
                else {
                    randomStringBuilder.putExcludedChar(RandomStringBuilder.SPECIAL);
                }

                randStr = randomStringBuilder.build();

                if((eng_s==false)&&(eng_l==false)&&(num==false)&&(spe==false))
                    randStr ="";

                textPassword.setText(String.valueOf(randStr));
            }
        });

        //저장 버튼 클릭 이벤트
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pw = textPassword.getText().toString();
                mDialogResult.finish(pw);
                dismiss();
            }
        });

        //취소 버튼 클릭 이벤트
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

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView.getId() == R.id.ENG_S) {
            if(isChecked){
                eng_s = true;
            }
            else{
                eng_s = false;
            }
        }
        if(buttonView.getId() == R.id.ENG_L) {
            if(isChecked){
                eng_l = true;
            }
            else{
                eng_l = false;
            }
        }
        if(buttonView.getId() == R.id.NUM) {
            if(isChecked){
                num = true;
            }
            else{
                num = false;
            }
        }
        if(buttonView.getId() == R.id.SPE) {
            if(isChecked){
                spe = true;
            }
            else{
                spe = false;
            }
        }

    }

}
