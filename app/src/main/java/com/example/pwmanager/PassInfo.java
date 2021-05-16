package com.example.pwmanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PassInfo extends AppCompatActivity {

    private Intent intent;
    private TextView textView;
    private String password;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passwordinfo); //전환된 xml


        intent = getIntent();

        password = intent.getStringExtra("TEXT");
        textView = findViewById(R.id.passinfo);
        textView.setText(password);


    }
}
