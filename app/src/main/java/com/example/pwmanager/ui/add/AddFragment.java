package com.example.pwmanager.ui.add;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pwmanager.Application;
import com.example.pwmanager.CipherUtils;
import com.example.pwmanager.R;
import com.example.pwmanager.StoreUtils;
import com.example.pwmanager.model.PasswordItem;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    private Button btnCrt;

    private TextView pushText;
    private Button btnPush;

    private int pushYear = 0;
    private int pushMonth = 0;
    private int pushDay = 0;

    private AlarmManager alarmManager;
    private NotificationManager notificationManager;
    NotificationCompat.Builder builder;

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
        pushText = root.findViewById(R.id.input_push);
        btnPush = root.findViewById(R.id.btn_selectPush);
        btnCrt = root.findViewById(R.id.btn_createPW);

        notificationManager = (NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        alarmManager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);


        //push 선택 버튼 클릭 이벤트
        btnPush.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

//                String[] array = getResources().getStringArray(R.array.Date);
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//
//                final int[] selectIndex = {0};
//
//                //제목 설정
//                builder.setTitle("알림 선택");
//
//                //확인 버튼 설정
//                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        pushText.setText(array[selectIndex[0]]);
//////                        Toast.makeText(getActivity(), "저장한것은:"+array[selectIndex[0]], Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                //취소 버튼 설정
//                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//
//                //라디오버튼 목록 설정
//                //첫번째 매개변수는 라디오버튼 항목을 문자열로 구성한 배열, 두번째는 초기에 선택되어지는 항목의 index
//                builder.setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //여러개의 라디오버튼들 중에서 선택된 인덱스를 저장
//                        selectIndex[0] = which;
//                    }
//                });
//
//                builder.show(); //다이얼로그 화면 출력

                //캘린더에 현재날짜 가져오기
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
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
                cp.show(getChildFragmentManager(), "create password");
                cp.setDialogResult(new CreatePassword.OnMyDialogResult() {
                    @Override
                    public void finish(String result) {
                        pwText.setText(result);
                    }
                });
            }
        });

        //저장 버튼 클릭 이벤트
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

            String push = pushText.getText().toString();
            boolean push_on_off = true;
            if(push.length()<=0){
                push=" ";
                push_on_off = false;
            }
            else {
                //on일때 알람 설정하기
                setAlarm();
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
            item.setPush(push);
            item.setPushOnOff(push_on_off);
//            item.setMonth(pushMonth);
//            item.setYear(pushYear);
            item.setMemo(memo);
            item.setDate(date);
            System.out.println("AddFragment: "+item); //저장됨 -> PasswordItem{}으로

            //PasswordItem 객체 저장
            saveItem(item);

            //입력 내용 초기화
            nameText.setText("");
            idText.setText("");
            urlText.setText("");
            pwText.setText("");
            pushText.setText("");
            memoText.setText("");
            date="";
            push_on_off=false;
//            month=0;
//            year=0;

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

    //알람 설정하는 함수
    private void setAlarm() {
        //AlarmReceiver에 값 전달
        Intent receiverIntent = new Intent(getActivity(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, receiverIntent, 0);

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