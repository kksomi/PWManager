package com.example.pwmanager.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pwmanager.MainActivity;
import com.example.pwmanager.R;

// 비밀번호 저장 전 앱 처음 화면
public class FirstLoginActivity extends AppCompatActivity {
    private Button btn_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_first);

        // 상단바 삭제
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btn_login = findViewById(R.id.btn_master_create);

        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstLoginActivity.this, FirstMasterCreateActivity.class); //로그인 액티비티에서 메인 액티비티로 이동하는 인텐트 설정
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //인텐트 플래그 설정
                startActivity(intent); //인텐트 이동
                finish(); //현재 로그인 액티비티 종료
            }
        });
    }
}
