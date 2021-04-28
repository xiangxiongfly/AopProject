package com.example.mylibrary.click;

import android.view.View;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;

import com.example.mylibrary.R;

public class ClickUtils {
    private static final long DEFAULT_INTERVAL = 1000L;

    public static boolean isSingleClick(@NonNull View target) {
        return isSingleClick(target, DEFAULT_INTERVAL);
    }

    public static boolean isSingleClick(@NonNull View target, @IntRange(from = 0) long interval) {
        long currentTime = System.currentTimeMillis();
        long lastTime;
        Object o = target.getTag(R.id.click_time);
        if (o == null) {
            target.setTag(R.id.click_time, currentTime);
            return true;
        }
        lastTime = (long) o;
        boolean isSingleClick = currentTime - lastTime > interval;
        if (isSingleClick) {
            target.setTag(R.id.click_time, currentTime);
        }
        return isSingleClick;
    }
}
