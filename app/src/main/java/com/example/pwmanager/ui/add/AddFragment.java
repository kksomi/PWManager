package com.example.pwmanager.ui.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pwmanager.Application;
import com.example.pwmanager.CipherUtils;
import com.example.pwmanager.R;
import com.example.pwmanager.StoreUtils;
import com.example.pwmanager.model.PasswordItem;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;

//비밀번호 추가 클래스
public class AddFragment extends Fragment {

    private AddViewModel viewModel;

    private TextInputEditText nameText;
    private TextInputEditText idText;
    private TextInputEditText urlText;
    private TextInputEditText pwText;
    private TextInputEditText memoText;
    private Button btn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(AddViewModel.class);
        //activity_insert.xml 화면 불러오기
        View root = inflater.inflate(R.layout.activity_insert, container, false);

        //activity_insert.xml 에서 입력한 내용을 변수에 저장
        nameText = root.findViewById(R.id.input_name);
        idText = root.findViewById(R.id.input_id);
        urlText = root.findViewById(R.id.input_url);
        pwText = root.findViewById(R.id.input_pw);
        memoText = root.findViewById(R.id.input_memo);
        btn = root.findViewById(R.id.input_button);

        //버튼 클릭 이벤트
        btn.setOnClickListener(v -> {
            String pw = null;
            String date = getTime(); //날짜

            //변수에서 텍스트를 문자열로 변환
            //입력이 없으면 공백으로 채움
            String name = nameText.getText().toString();
            if(name.length()<=0){
                name=" ";
            }
            String id = idText.getText().toString();
            if(id.length()<=0){
                id=" ";
            }
            String url = urlText.getText().toString();
            if(url.length()<=0){
                url=" ";
            }
            String memo = memoText.getText().toString();
            if(memo.length()<=0){
                memo=" ";
            }
            String pwPlainText = pwText.getText().toString();
            if(pwPlainText.length()<=0){
                pwPlainText=" ";
            }
            //비밀번호는 CipherUtils 를 통해 암호화하여 저장
            try {
                pw = CipherUtils.encrypt(pwPlainText, Application.getMasterKey());
            } catch (Exception e) {
                e.printStackTrace();
            }

            //PasswordItem 객체 생성 및 저장된 내용 추가
            PasswordItem item = new PasswordItem();

            item.setName(name);
            item.setId(id);
            item.setUrl(url);
            item.setEncryptPassword(pw);
            item.setMemo(memo);
            item.setDate(date);

            //PasswordItem 객체 저장
            saveItem(item);

            //입력 내용 초기화
            nameText.setText("");
            idText.setText("");
            urlText.setText("");
            pwText.setText("");
            memoText.setText("");
            date="";

        });

        return root;
    }

    //PasswordItem 객체 저장 함수
    private void saveItem(PasswordItem item) {
        //StoreUtils 객체 생성 및 전달 받은 PasswordItem 객체 저장 후 메시지 출력
        new StoreUtils(getContext()).addItem(item);
        Toast.makeText(getContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();

    }

    //현재 시간 불러오는 함수
    private String getTime(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(date);
    }
}