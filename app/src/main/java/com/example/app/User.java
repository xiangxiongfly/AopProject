package com.example.app;

import android.util.Log;

public class User {
    public void eat() {
        Log.e("AOP", "User#eat()");
    }

    public String run(String params) {
        Log.e("AOP", "User#run()");
        return params;
    }
}
