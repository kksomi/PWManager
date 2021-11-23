package com.example.pwmanager.ui.setting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pwmanager.R;
import com.example.pwmanager.ThemeUtil;

public class SettingThemeActivity extends AppCompatActivity {

    RadioButton r_btn_light,r_btn_dark;
    String themeColor;
    SharedPreferences settings;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_theme);

        // 상단바 삭제
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        settings = getSharedPreferences("Answer_theme",0);
        editor = settings.edit();

        r_btn_light = findViewById(R.id.radioButton);
        r_btn_dark = findViewById(R.id.radioButton2);

        r_btn_light.setChecked(true);

        r_btn_light.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themeColor = ThemeUtil.LIGHT_MODE;
                ThemeUtil.applyTheme(themeColor);
                ThemeUtil.modSave(getApplicationContext(),themeColor);
                editor.putBoolean("r_btn_light",true);
                editor.putBoolean("r_btn_dark",false);
                editor.commit();
                }
        }));

        r_btn_dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themeColor = ThemeUtil.DARK_MODE;
                ThemeUtil.applyTheme(themeColor);
                ThemeUtil.modSave(getApplicationContext(),themeColor);
                editor.putBoolean("r_btn_light",false);
                editor.putBoolean("r_btn_dark",true);
                editor.commit();
            }
        });
        //데이터 가져오기
        Intent intent = getIntent();

        boolean btn_light = settings.getBoolean("r_btn_light",false);
        boolean btn_dark = settings.getBoolean("r_btn_dark",false);

        r_btn_light.setChecked(btn_light);
        r_btn_dark.setChecked(btn_dark);
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