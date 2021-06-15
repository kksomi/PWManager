package com.example.pwmanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class PassInfo extends AppCompatActivity {

    private Intent intent;
    private TextView textView;
    private String password;
    private passinfoAdapter adapter;
    private int num;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passwordinfo); //전환된 xml


        intent = getIntent();

        password = intent.getStringExtra("TEXT");
        num = intent.getIntExtra("num",0);
        textView = findViewById(R.id.passinfo);
        textView.setText(password);

        init();
        getData();

    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.passinfore);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new passinfoAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        // 임의의 데이터입니다.
        if(num==1){
        List<String> listTitle = Arrays.asList("Google", "Naver", "Daum", "Kakao", "Dankook", "Yahoo");
        List<String> listContent = Arrays.asList(
                "구글",
                "네이버",
                "다음",
                "카카오",
                "단국대학교",
                "야후"
        );

        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Data data = new Data();
            data.setTitle(listTitle.get(i));
            data.setContent(listContent.get(i));
            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }
    else if(num==2){
        List<String> listTitle = Arrays.asList("URL", "아이디", "비밀번호", "알림", "메모");
        List<String> listContent = Arrays.asList(
                "www.google.com",
                "id",
                "password",
                "OFF",
                "Memo"
        );

        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Data data = new Data();
            data.setTitle(listTitle.get(i));
            data.setContent(listContent.get(i));
            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }
}
}
