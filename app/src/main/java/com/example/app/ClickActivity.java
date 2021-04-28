package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mylibrary.click.ClickUtils;
import com.example.mylibrary.click.SingleClick;
import com.example.mylibrary.click.SingleClickListener;

public class ClickActivity extends AppCompatActivity {
    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);


        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtils.isSingleClick(v)) {
                    Log.e(TAG, "click");
                } else {
                    Log.e(TAG, "invalid");
                }
            }
        });


        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new SingleClickListener() {
            @Override
            protected void onSingleClick(View v) {
                Log.e(TAG, "click");
            }
        });


        Button btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {

            @SingleClick(2000L)
            @Override
            public void onClick(View v) {
                Log.e(TAG, "click");
            }
        });
    }

}