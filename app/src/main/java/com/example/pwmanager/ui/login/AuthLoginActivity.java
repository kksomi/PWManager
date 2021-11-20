package com.example.pwmanager.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.example.pwmanager.MainActivity;
import com.example.pwmanager.R;

import java.util.concurrent.Executor;

public class AuthLoginActivity extends AppCompatActivity {

    private Button btn_login,btn_auth_login;
    private TextView pw_key;
    private String master_password;
    private String master_password_pref; // 저장소에서 불러올 비밀번호

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_login);


        btn_login = findViewById(R.id.btn_login);
        btn_auth_login = findViewById(R.id.btn_auth_login);
        pw_key = findViewById(R.id.pw_key);

        SharedPreferences MyPref= getSharedPreferences("MyPref", MODE_PRIVATE);
        // default 값 : 저장소에서 가져올 값이 없을때 주어질 값
        master_password_pref = MyPref.getString("master_password","");

        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(this,
                executor, new BiometricPrompt.AuthenticationCallback() {

            @Override
            public void onAuthenticationError(int errorCode,
                                              @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(),
                        "지문 인식에 실패하였습니다", Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onAuthenticationSucceeded(
                    @NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(),
                        "로그인 되었습니다.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AuthLoginActivity.this, MainActivity.class); //로그인 액티비티에서 메인 액티비티로 이동하는 인텐트 설정
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //인텐트 플래그 설정
                startActivity(intent); //인텐트 이동
                finish(); //현재 로그인 액티비티 종료
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "지문 인식 에러",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });


        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("지문 인증")
                .setSubtitle("기기에 등록된 지문을 이용하여 지문을 인증해주세요.")
                .setNegativeButtonText("취소")
                .setDeviceCredentialAllowed(false)
                .build();

        auth();


        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                master_password = pw_key.getText().toString();
                if (master_password.equals(master_password_pref)) {
                    Toast.makeText(getApplicationContext(), "로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AuthLoginActivity.this, MainActivity.class); //로그인 액티비티에서 메인 액티비티로 이동하는 인텐트 설정
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //인텐트 플래그 설정
                    startActivity(intent); //인텐트 이동
                    finish(); //현재 로그인 액티비티 종료
                } else {
                    Toast.makeText(getApplicationContext(), "비밀번호를 다시 입력 하시오.", Toast.LENGTH_SHORT).show();
                    pw_key.setText("");
                }
            }
        });

        // 버튼 클릭 시 지문 장금 해제 창 뜨기
        btn_auth_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                biometricPrompt.authenticate(promptInfo);
            }
        });
    }
    public void auth(){
        biometricPrompt.authenticate(promptInfo);
    }
}
