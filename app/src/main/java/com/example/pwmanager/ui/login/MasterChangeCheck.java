package com.example.pwmanager.ui.login;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pwmanager.R;

public class MasterChangeCheck extends AppCompatActivity {

    private EditText prev_master;
    private String prev_master_str;
    private String master_password_pref;
    private Button btn_continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.master_change_check);

        // 상단바 삭제
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        prev_master = findViewById(R.id.prev_master);
        btn_continue = findViewById(R.id.btn_continue);

        SharedPreferences MyPref= getSharedPreferences("MyPref", MODE_PRIVATE);
        // default 값 : 저장소에서 가져올 값이 없을때 주어질 값
        master_password_pref = MyPref.getString("master_password","");

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prev_master_str = prev_master.getText().toString();

                if (prev_master_str.equals(master_password_pref)) {
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치합니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MasterChangeCheck.this, MasterChange.class); //로그인 액티비티에서 메인 액티비티로 이동하는 인텐트 설정
                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //인텐트 플래그 설정
                    startActivity(intent); //인텐트 이동
                    //finish(); //현재 로그인 액티비티 종료
                } else {
                    Toast.makeText(getApplicationContext(), "비밀번호를 다시 입력 하시오.", Toast.LENGTH_SHORT).show();
                    prev_master.setText("");
                }
            }
        });
    }
}