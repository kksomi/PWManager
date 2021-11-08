package com.example.pwmanager.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pwmanager.R;
import com.example.pwmanager.ThemeUtil;

public class SettingThemeActivity extends AppCompatActivity {

    Button r_btn_light,r_btn_dark;
    String themeColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_theme);

        r_btn_light = findViewById(R.id.radioButton);
        r_btn_dark = findViewById(R.id.radioButton2);

        r_btn_light.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themeColor = ThemeUtil.LIGHT_MODE;
                ThemeUtil.applyTheme(themeColor);
                ThemeUtil.modSave(getApplicationContext(),themeColor);
            }
        }));

        r_btn_dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themeColor = ThemeUtil.DARK_MODE;
                ThemeUtil.applyTheme(themeColor);
                ThemeUtil.modSave(getApplicationContext(),themeColor);
            }
        });
        //데이터 가져오기
        Intent intent = getIntent();
    }

    //취소, 확인 버튼 클릭
    public void mOnClose(View v){
        //데이터 전달하기
        Intent intent = new Intent();
        intent.putExtra("result", "Close Popup");
        setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

}