package com.example.pwmanager.ui.list;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pwmanager.Application;
import com.example.pwmanager.CipherUtils;
import com.example.pwmanager.R;
import com.example.pwmanager.model.PasswordItem;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDialog extends Dialog {
    private OnDialogListener listener;
    private Context context;
    private TextInputEditText nameText;
    private TextInputEditText idText;
    private TextInputEditText urlText;
    private TextInputEditText pwText;
    private TextInputEditText memoText;
    private Button btn;

    private TextView pushText;
    private String name, url, id, pw, push, memo;
    private boolean push_on_off;
    private int month,year;
    public CustomDialog(Context context, final int position, PasswordItem item) {
        super(context);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.activity_insert);

        name = item.getName();
        url = item.getUrl();
        id = item.getId();
        try {
            String plainPw = CipherUtils.decrypt(item.getEncryptPassword(), Application.getMasterKey());
            pw = plainPw;
        } catch (Exception e) {
            e.printStackTrace();
        }
        push = item.getPush();
        memo = item.getMemo();
        push_on_off = item.getPushOnOff();
        month = item.getMonth();
        year = item.getYear();

        //값 채우기
        nameText = findViewById(R.id.input_name);
        nameText.setText(name);
        urlText = findViewById(R.id.input_url);
        urlText.setText(url);
        idText = findViewById(R.id.input_id);
        idText.setText(id);
        pwText = findViewById(R.id.input_pw);
        pwText.setText(pw);
        pushText = findViewById(R.id.input_push);
        pushText.setText(push);
        memoText = findViewById(R.id.input_memo);
        memoText.setText(memo);
        btn = findViewById(R.id.input_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) {
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
                    String push = pushText.getText().toString();
                    if(push.length()<=0){
                        push=" ";
                    }
                    else{
                        if(push.equals("없음")){
                            push_on_off = false;
                        }
                        else{
                            push_on_off = true;
                            if(push.equals("1개월 후")){
                                month = 1;
                            }
                            else if(push.equals("2개월 후")){
                                month = 2;
                            }
                            else if(push.equals("3개월 후")){
                                month = 3;
                            }
                            else if(push.equals("6개월 후")){
                                month = 6;
                            }
                            else if(push.equals("1년 후")){
                                year = 1;
                            }
                            else if(push.equals("2년 후")){
                                year = 2;
                            }
                            else if(push.equals("3년 후")){
                                year = 3;
                            }
                        }
                    }
                    String memo = memoText.getText().toString();
                    if(memo.length()<=0){
                        memo=" ";
                    }
                    String pwPlainText = pwText.getText().toString();
                    if(pwPlainText.length()<=0){
                        pwPlainText=" ";
                    }
                    String date = getTime();
                    String pw = null;
                    //비밀번호는 CipherUtils 를 통해 암호화하여 저장
                    try {
                        pw = CipherUtils.encrypt(pwPlainText, Application.getMasterKey());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //PasswordItem 객체 생성 및 저장된 내용 추가
                    PasswordItem item2 = new PasswordItem();

                    item2.setName(name);
                    item2.setId(id);
                    item2.setUrl(url);
                    item2.setEncryptPassword(pw);
                    item2.setPush(push);
                    item2.setPushOnOff(push_on_off);
                    item2.setMonth(month);
                    item2.setYear(year);
                    item2.setMemo(memo);
                    item2.setDate(date);

                    System.out.println(item2);

                    //입력 내용 초기화
                    nameText.setText("");
                    idText.setText("");
                    urlText.setText("");
                    pwText.setText("");
                    pushText.setText("");
                    memoText.setText("");
                    date="";
                    push_on_off=false;
                    month=0;
                    year=0;

                    //Listener를 통해 password객체 전달
                    listener.onFinish(position, item2);

                    //다이얼로그 종료
                    dismiss();
                }
            }
        });
    }

    public void setDialogListener(OnDialogListener listener) {
        this.listener = listener;
    }

    //현재 시간 불러오는 함수
    private String getTime(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(date);
    }

}
