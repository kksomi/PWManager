package com.example.pwmanager.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pwmanager.MainActivity;
import com.example.pwmanager.R;
import com.example.pwmanager.ui.setting.SettingMasterActivity;

public class MasterChangeComplete extends AppCompatActivity {

    Button btn_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.master_change_complete);

        btn_check = findViewById(R.id.btn_check);

        btn_check.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 저장하고 mainactivity로 이동
                Intent intent = new Intent(MasterChangeComplete.this, SettingMasterActivity.class); //로그인 액티비티에서 메인 액티비티로 이동하는 인텐트 설정
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //상위 스택 제거
                startActivity(intent); // 엑티비티 호출하는 메소드
                finish(); //현재 로그인 액티비티 종료
            }
        }));
    }
}