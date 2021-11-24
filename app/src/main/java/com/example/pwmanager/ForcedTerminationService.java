package com.example.pwmanager;

import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class ForcedTerminationService extends Service{


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.e("Error","강제 종료"+rootIntent);
        Toast.makeText(this,"강제종료",Toast.LENGTH_SHORT).show();
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("","");
        clipData = ClipData.newPlainText("ID","");
        clipboardManager.setPrimaryClip(clipData);

        stopSelf();
    }

}
