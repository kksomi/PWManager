package com.example.pwmanager.ui.setting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pwmanager.MainActivity;
import com.example.pwmanager.R;
import com.example.pwmanager.ui.login.FirstMasterCreateActivity;
import com.example.pwmanager.ui.login.MasterChangeCheck;

public class SettingMasterActivity extends AppCompatActivity {

    private TextView master_change;
    private CheckBox checkBox;
    SharedPreferences settings;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_master);

        settings = getSharedPreferences("Answer_auth",0);
        editor = settings.edit();

        master_change = findViewById(R.id.master_change);
        checkBox = findViewById(R.id.checkbox);

        master_change.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingMasterActivity.this, MasterChangeCheck.class);
                startActivity(intent);
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckState();
            }
        });

        // 저장된 switch 상태 불러오고 상태 유지
        boolean btn_state = settings.getBoolean("c_btn_state",false);
       //  boolean btn_off = settings.getBoolean("s_btn_state",true);

        // sw.setChecked(btn_on);
        checkBox.setChecked(btn_state);

        //Intent intent = getIntent();
    }

    public void CheckState() {
        if(checkBox.isChecked()) {
            editor.putBoolean("c_btn_state",true);
            editor.commit();
        } else {
            editor.putBoolean("c_btn_state",false);
            editor.commit();
        }
    }

    //취소, 확인 버튼 클릭
    public void myOnClose(View v){
        //데이터 전달하기
        Intent intent = new Intent();
        intent.putExtra("result", "Close Popup");
        setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        finish();
    }
}