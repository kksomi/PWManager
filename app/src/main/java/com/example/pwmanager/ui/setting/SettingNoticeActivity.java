package com.example.pwmanager.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pwmanager.R;

public class SettingNoticeActivity extends AppCompatActivity {

    Switch chkAgree;
    TextView txt1, txt2;
    CheckBox cb1, cb2;
    TimePicker time1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_notice);

        Intent intent = getIntent();

        chkAgree = findViewById(R.id.chkAgree);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        cb1 = findViewById(R.id.checkBox1);
        cb2 = findViewById(R.id.checkBox2);
        time1 = findViewById(R.id.time);

        time1.setEnabled(false);

        chkAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chkAgree.isChecked()) {
                    txt1.setEnabled(true);
                    txt2.setEnabled(true);
                    cb1.setEnabled(true);
                    cb2.setEnabled(true);
                    time1.setEnabled(true);
                } else {
                    txt1.setEnabled(false);
                    txt2.setEnabled(false);
                    cb1.setEnabled(false);
                    cb2.setEnabled(false);
                    time1.setEnabled(false);
                }

            }
        });

    }

}