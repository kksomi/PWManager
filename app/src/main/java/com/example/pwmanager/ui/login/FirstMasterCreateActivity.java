// 처음 master비밀번호를 만드는 activity
package com.example.pwmanager.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pwmanager.MainActivity;
import com.example.pwmanager.R;

public class FirstMasterCreateActivity extends AppCompatActivity {

    // 뷰 객체 생성
    private Button btn_login;
    private EditText masterEt; // 마스터 비밀번호 입력

    // 변수 객체 생성
    String master_password;

    // sharedpreference 변수 선언
    SharedPreferences MyPref ;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_first_create);

        btn_login = findViewById(R.id.btn_master_create_done);
        masterEt = findViewById(R.id.masterEt); // master비밀번호

        // 상단바 삭제
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // 완료 버튼 클릭
        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                master_password = masterEt.getText().toString();
                if (master_password.equals("")) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    masterEt.requestFocus(); // mater입력창으로 커서 이동

                    //키보드 보이게 하는 부분
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                } else {
                    // master 비밀번호를 모두 입력했다면 회원가입 정보 shared에 저장
                    signCheck();

                    // 저장하고 mainactivity로 이동
                    Intent intent = new Intent(FirstMasterCreateActivity.this, MainActivity.class); //로그인 액티비티에서 메인 액티비티로 이동하는 인텐트 설정
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //상위 스택 제거
                    startActivity(intent); // 엑티비티 호출하는 메소드
                    finish(); //현재 로그인 액티비티 종료
                }
            }
        });
    }

    // 비밀번호 저장
    protected void signCheck() {
        // shared preference 변수 초기화
        // 파일명에 대한 정보를 매개변수로 지정 -> 해당 이름으로 xml파일 제작
        MyPref = this.getSharedPreferences("MyPref",FirstMasterCreateActivity.MODE_PRIVATE);
        editor = MyPref.edit();
        // string key 값과 저장할 값
        editor.putString("master_password",master_password);
        editor.commit();
        Toast.makeText(getApplicationContext(), "비밀번호가 저장되었습니다.", Toast.LENGTH_SHORT).show();
    }
}
