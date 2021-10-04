package com.example.pwmanager;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView; // 바텀 네비게이션 뷰
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Fragment activity_password;
    private Fragment activity_web;
    private Fragment activity_insert;
    private Fragment activity_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
//                    case R.id.action_password:
//                        setFrag(0);
//                        break;
                    case R.id.action_web:
                        setFrag(1);
                        break;
                    case R.id.action_insert:
                        setFrag(2);
                        break;
                    case R.id.action_setting:
                        setFrag(3);
                        break;
                }
                return true;
            }
        });

//        activity_password = new PasswordFrag();
        activity_web = new WebFrag();
        activity_insert = new InsertFrag();
        activity_setting = new SettingsFrag();
        setFrag(1); // 첫 프래그먼트 화면 지정

    }

    // 프레그먼트 교체
    private void setFrag(int n)
    {
        fm = getSupportFragmentManager();
        ft= fm.beginTransaction();
        switch (n)
        {
//            case 0:
//                ft.replace(R.id.Main_Frame,activity_password);
//                ft.commit();
//                break;

            case 1:
                ft.replace(R.id.Main_Frame,activity_web);
                ft.commit();
                break;

            case 2:
                ft.replace(R.id.Main_Frame,activity_insert);
                ft.commit();
                break;

            case 3:
                ft.replace(R.id.Main_Frame,activity_setting);
                ft.commit();
                break;
        }
    }
}