package com.example.pwmanager.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pwmanager.MainActivity;
import com.example.pwmanager.R;
import com.example.pwmanager.ui.login.FirstMasterCreateActivity;
import com.example.pwmanager.ui.login.MasterChangeCheck;

public class SettingMasterActivity extends AppCompatActivity {

    private TextView master_change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_master);

        master_change = findViewById(R.id.master_change);

        master_change.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingMasterActivity.this, MasterChangeCheck.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
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