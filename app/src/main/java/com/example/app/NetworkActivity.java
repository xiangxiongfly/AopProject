package com.example.app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylibrary.network.CheckNet;

public class NetworkActivity extends AppCompatActivity {
    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserInfo();
            }
        });

        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserInfo2();
            }
        });
    }

    @CheckNet()
    private void getUserInfo() {
        Log.e(TAG, "获取用户信息");
    }

    @CheckNet(CheckNet.SHOW_DIALOG)
    private void getUserInfo2() {
        Log.e(TAG, "获取用户信息2");
    }
}