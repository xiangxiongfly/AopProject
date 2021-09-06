package com.example.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        User user = new User();
        user.eat();
        user.run("hello");
    }

    public void gotoClickActivity(View view) {
        startActivity(new Intent(context, ClickActivity.class));
    }

    public void gotoNetworkActivity(View view) {
        startActivity(new Intent(context, NetworkActivity.class));
    }
}