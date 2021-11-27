package com.example.pwmanager.ui.add;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.pwmanager.MainActivity;
import com.example.pwmanager.R;
import com.example.pwmanager.model.PasswordItem;
import com.example.pwmanager.ui.add.AddFragment;
import com.example.pwmanager.ui.list.CustomDialog;
import com.example.pwmanager.ui.list.DetailFragment;
import com.example.pwmanager.ui.list.ListFragment;
import com.example.pwmanager.ui.list.ListViewModel;
import com.example.pwmanager.ui.login.LoginActivity;

public class AlarmReceiver extends BroadcastReceiver {

    public AlarmReceiver(){ }

    NotificationManager notificationManager;
    NotificationCompat.Builder builder;

    //채널 설정해줘야 Notification이 작동
    private static String CHANNEL_ID = "channel1";
    private static String CHANNEL_NAME = "Channel1";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        builder = null;
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                    new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT)
            );
            builder = new NotificationCompat.Builder(context, CHANNEL_ID);
        }
        else {
            builder = new NotificationCompat.Builder(context);
        }

       //알림창 클릭 시 activity 화면 부름
        Intent intent2 = new Intent(context, LoginActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 101, intent2, PendingIntent.FLAG_UPDATE_CURRENT);

        //알림창 제목
        builder.setContentTitle("알림");
        //알림창 내용
        builder.setContentText("비밀번호를 변경해야하는 날입니다.");
        //알림창 아이콘
        builder.setSmallIcon(R.drawable.login);
        //알림 중요도
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        //알림창 터치 시 삭제
        builder.setAutoCancel(true);

        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();
        notificationManager.notify(1,notification);
    }
}
