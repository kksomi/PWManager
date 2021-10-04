package com.example.pwmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertFrag extends Fragment {

    PreferenceManager pref;

    private EditText siteName;
    private EditText siteURL;
    private EditText userId;
    private EditText userPassword;
    private EditText userPush;
    private EditText userMemo;
    private Button saveBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_insert, container,  false);

        pref = new PreferenceManager();
        //findViewById로 할당
        EditText siteName = (EditText) v.findViewById(R.id.siteName);
        EditText siteURL = (EditText) v.findViewById(R.id.siteURL);
        EditText userId = (EditText) v.findViewById(R.id.userId);
        EditText userPassword = (EditText) v.findViewById(R.id.userPassword);
        EditText userPush = (EditText) v.findViewById(R.id.userPush);
        EditText userMemo = (EditText) v.findViewById(R.id.userMemo);
        Button saveBtn = (Button) v.findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() { //저장버튼 눌렀을 때
            @Override
            public void onClick(View v) {
                //String 값으로 변환
                String site_name = siteName.getText().toString();
                String site_url = siteURL.getText().toString();
                String user_id = userId.getText().toString();
                String user_pw = userPassword.getText().toString();
                String user_push = userPush.getText().toString();
                String user_memo = userMemo.getText().toString();

                //String 값을 JSONObject로 변환하여 사용할 수 있도록 사이트이름, 유저의아이디 등을 JSON 형식으로 저장
                String save_form = "{\"name\":\""+site_name+"\",\"url\":\""+site_url+"\",\"id\":\""+user_id+"\",\"pw\":\""+user_pw+"\",\"push\":\""+user_push+"\",\"memo\":\""+user_memo+"\"}";

                //Key값이 겹치지 않도록 현재 시간으로 부여
                long now = System.currentTimeMillis();
                Date mDate = new Date(now);
                SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String getTime = simpleDate.format(mDate).toString();

                Log.d("InsertFrag","이름:"+site_name+", 아이디:"+user_id+", 현재시간:"+getTime);

                //PreferenceManager 클래스에서 저장에 관한 메소드 관리
                pref.setString(getActivity().getApplicationContext(),getTime,save_form);

            }
        });

        return v;
    }


    //PreferenceManager
    public static class PreferenceManager {
        public static final String PREFERENCES_NAME = "info";
        private static final String DEFALUT_VALUE_STRING = " ";
        private static final boolean DEFALUT_VALUE_BOOLEAN = false;
        private static final int DEFALUT_VALUE_INT = -1;
        private static final long DEFALUT_VALUE_LONG = -1L;
        private static final float DEFALUT_VALUE_FLOAT = -1F;

        private static SharedPreferences getPreferences(Context context) {
            return context.getSharedPreferences(PREFERENCES_NAME, context.MODE_PRIVATE);
        }

        /*  String 값 저장
            param context
            param key
            param value
        */
        public static void setString(Context context,String key, String value){
            SharedPreferences prefs = getPreferences(context);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(key, value);
            editor.apply();
        }

        /*  String 값 로드
            param context
            param key
            return
        */
        public static String getString(Context context, String key){
            SharedPreferences prefs = getPreferences(context);
            String value = prefs.getString(key, DEFALUT_VALUE_STRING);
            return value;
        }

        /*  키 값 삭제
            param context
            param key
        */
        public  static void removeKey(Context context, String key){
            SharedPreferences prefs = getPreferences(context);
            SharedPreferences.Editor editor = prefs.edit();
            editor.remove(key);
            editor.commit();
        }

        /*  모든 저장 데이터 삭제
            param  context
        */
        public static void clear(Context context){
            SharedPreferences prefs = getPreferences(context);
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.commit();
        }

    }


}
