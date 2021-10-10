package com.example.pwmanager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

//    private BottomNavigationView bottomNavigationView; // 바텀 네비게이션 뷰
//    private FragmentManager fm;
//    private FragmentTransaction ft;
//    private Fragment activity_password;
//    private Fragment activity_web;
//    private Fragment activity_insert;
//    private Fragment activity_setting;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        bottomNavigationView = findViewById(R.id.bottomNavi);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()) {
////                    case R.id.action_password:
////                        setFrag(0);
////                        break;
//                    case R.id.action_web:
//                        setFrag(1);
//                        break;
//                    case R.id.action_insert:
//                        setFrag(2);
//                        break;
//                    case R.id.action_setting:
//                        setFrag(3);
//                        break;
//                }
//                return true;
//            }
//        });
//
////        activity_password = new PasswordFrag();
//        activity_web = new WebFrag();
//        activity_insert = new InsertFrag();
//        activity_setting = new SettingsFrag();
//        setFrag(1); // 첫 프래그먼트 화면 지정
//
//    }
//
//    // 프레그먼트 교체
//    private void setFrag(int n)
//    {
//        fm = getSupportFragmentManager();
//        ft= fm.beginTransaction();
//        switch (n)
//        {
////            case 0:
////                ft.replace(R.id.Main_Frame,activity_password);
////                ft.commit();
////                break;
//
//            case 1:
//                ft.replace(R.id.Main_Frame,activity_web);
//                ft.commit();
//                break;
//
//            case 2:
//                ft.replace(R.id.Main_Frame,activity_insert);
//                ft.commit();
//                break;
//
//            case 3:
//                ft.replace(R.id.Main_Frame,activity_setting);
//                ft.commit();
//                break;
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_list, R.id.navigation_add, R.id.navigation_setting)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }
}