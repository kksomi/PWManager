package com.example.pwmanager.ui.list;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.style.TtsSpan;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.pwmanager.Application;
import com.example.pwmanager.CipherUtils;
import com.example.pwmanager.R;
import com.example.pwmanager.model.PasswordItem;
import com.example.pwmanager.ui.add.AlarmReceiver;
import com.example.pwmanager.ui.add.CreatePassword;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    private Button btnPush,btnDelete,btnCrt;

    private TextView pushText;
    private String name, url, id, pw, push, memo;
    private boolean push_on_off;
    private int pushYear,pushMonth,pushDay;

    private AlarmManager alarmManager;
    private NotificationManager notificationManager;
    NotificationCompat.Builder builder;

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
        btnPush = findViewById(R.id.btn_selectPush);
        btnDelete = findViewById(R.id.btn_deletePush);
        btnCrt = findViewById(R.id.btn_createPW);

        notificationManager = (NotificationManager)getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        alarmManager = (AlarmManager)getContext().getSystemService(Context.ALARM_SERVICE);

        btnDelete.setVisibility(View.VISIBLE);

        //알림 삭제 버튼
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushText.setText(null);
            }
        });

        btnPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //캘린더에 현재날짜 가져오기
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) { //month+1 이 선택한 월
                        pushText.setText(String.format("%d-%d-%d",year,month+1,dayOfMonth));
                        pushYear = year;
                        pushMonth = month+1;
                        pushDay = dayOfMonth;
                    }
                }, year, month, day);

                //현재 날짜 이전에는 선택할 수 없도록 설정
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());

                datePickerDialog.show();
            }
        });


        btnCrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreatePassword cp = new CreatePassword();
                cp.setDialogResult(new CreatePassword.OnMyDialogResult() {
                    @Override
                    public void finish(String result) {
                        pwText.setText(result);
                    }
                });
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    String name = nameText.getText().toString();
                    if (name.length() <= 0) {
                        name = " ";
                    }
                    String id = idText.getText().toString();
                    if (id.length() <= 0) {
                        id = " ";
                    }
                    String url = urlText.getText().toString();
                    if (url.length() <= 0) {
                        url = " ";
                    }
                    String push = pushText.getText().toString();
                    if (push.length() <= 0 | push.equals(" ")) {
                        push = " ";
                        push_on_off = false;
                    } else {
                        push_on_off = true;
                        //on일때 알람 설정하기
                        setAlarm();
                    }
                    String memo = memoText.getText().toString();
                    if (memo.length() <= 0) {
                        memo = " ";
                    }
                    String pwPlainText = pwText.getText().toString();
                    if (pwPlainText.length() <= 0) {
                        pwPlainText = " ";
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
                    date = "";
                    push_on_off = false;

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

    //알람 설정하는 함수
    private void setAlarm() {
        //AlarmReceiver에 값 전달
        Intent receiverIntent = new Intent(getContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, receiverIntent, 0);

        //지정한 날짜에 알림 설정 -> 시간은 모두 오후 12시에 울리도록
        String date = String.format("%d-%d-%d 12:00:00", pushYear, pushMonth, pushDay);
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datetime = null;

        try {
            datetime = timeFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datetime);

        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(),pendingIntent);
    }

}
