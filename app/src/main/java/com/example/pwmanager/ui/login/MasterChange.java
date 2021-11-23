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

public class MasterChange extends AppCompatActivity {

    private EditText newMaster;
    private Button btn_change;
    private String newMasterStr;

    // sharedpreference 변수 선언
    SharedPreferences MyPref ;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.master_change);

        // 상단바 삭제
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        newMaster = findViewById(R.id.newMaster);
        btn_change = findViewById(R.id.btn_change);

        btn_change.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                newMasterStr = newMaster.getText().toString();

                changeMaster();

                // 저장하고 mainactivity로 이동
                Intent intent = new Intent(MasterChange.this, MasterChangeComplete.class); //로그인 액티비티에서 메인 액티비티로 이동하는 인텐트 설정
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //상위 스택 제거
                startActivity(intent); // 엑티비티 호출하는 메소드
            }
        });
    }

    protected void changeMaster() {
        MyPref = getSharedPreferences("MyPref",FirstMasterCreateActivity.MODE_PRIVATE);
        editor = MyPref.edit();

        editor.putString("master_password",newMasterStr);
        editor.commit();
        Toast.makeText(getApplicationContext(), "비밀번호가 수정되었습니다.", Toast.LENGTH_SHORT).show();
    }
}