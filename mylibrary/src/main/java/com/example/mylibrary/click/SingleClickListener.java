package com.example.mylibrary.click;

import android.view.View;

public abstract class SingleClickListener implements View.OnClickListener {
    private static final long DEFAULT_INTERVAL = 1000L;
    private long interval;
    private long lastTime;

    public SingleClickListener() {
        this(DEFAULT_INTERVAL);
    }

    public SingleClickListener(long interval) {
        this.interval = interval;
    }

    @Override
    public void onClick(View v) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime > interval) {
            onSingleClick(v);
            lastTime = currentTime;
        }
    }

    protected abstract void onSingleClick(View v);
}
