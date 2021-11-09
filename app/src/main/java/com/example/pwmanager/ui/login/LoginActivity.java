package com.example.pwmanager.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pwmanager.MainActivity;
import com.example.pwmanager.R;


public class LoginActivity extends AppCompatActivity {

    private Button btn_login;
    private TextView pw_key;
    private String master_password;
    private String master_password_pref; // 저장소에서 불러올 비밀번호

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_login);
        pw_key = findViewById(R.id.pw_key);

        SharedPreferences MyPref= getSharedPreferences("MyPref", MODE_PRIVATE);
        // default 값 : 저장소에서 가져올 값이 없을때 주어질 값
        master_password_pref = MyPref.getString("master_password","");

        if (master_password_pref.equals("")) {
            Intent intent = new Intent(LoginActivity.this, FirstLoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //인텐트 플래그 설정
            startActivity(intent); //인텐트 이동
            finish();
        }

        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                master_password = pw_key.getText().toString();
                if (master_password.equals(master_password_pref)) {
                    Toast.makeText(getApplicationContext(), "로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class); //로그인 액티비티에서 메인 액티비티로 이동하는 인텐트 설정
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //인텐트 플래그 설정
                    startActivity(intent); //인텐트 이동
                    finish(); //현재 로그인 액티비티 종료
                } else {
                    Toast.makeText(getApplicationContext(), "비밀번호를 다시 입력 하시오.", Toast.LENGTH_SHORT).show();
                    pw_key.setText("");
                }
            }
        });
    }
}