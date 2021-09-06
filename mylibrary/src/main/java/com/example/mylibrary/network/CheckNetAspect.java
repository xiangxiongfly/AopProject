package com.example.mylibrary.network;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class CheckNetAspect {

    @Pointcut("execution(@com.example.mylibrary.network.CheckNet * *(..))")
    public void checkNetMethod() {
    }

    @Around("checkNetMethod()")
    public void handleCheckNet(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        CheckNet checkNet = methodSignature.getMethod().getAnnotation(CheckNet.class);
        if (checkNet != null) {
            Object aThis = joinPoint.getThis();
            Context context = getContext(aThis);
            if (context != null && !isNetworkAvailable(context)) {
                if (checkNet.value() == CheckNet.SHOW_TIP) {
                    showTip(context);
                } else if (checkNet.value() == CheckNet.SHOW_DIALOG) {
                    showSettingDialog(context);
                }
                return;
            }
        }
        joinPoint.proceed();
    }

    /**
     * 显示提示
     */
    private void showTip(Context context) {
        Toast.makeText(context, "当前没有网络连接，请检查网络设置", Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示弹窗
     */
    private void showSettingDialog(Context context) {
        new AlertDialog.Builder(context)
                .setMessage("当前没有网络连接，请检查网络设置")
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        context.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    }
                })
                .show();
    }

    /**
     * 判断网络是否能用
     */
    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager = ContextCompat.getSystemService(context, ConnectivityManager.class);
        if (manager != null) {
            NetworkInfo info = manager.getActiveNetworkInfo();
            // 判断网络是否连接
            if (info == null || !info.isConnected()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取Context对象
     */
    private Context getContext(Object object) {
        if (object instanceof Activity) {
            return (Activity) object;
        } else if (object instanceof Fragment) {
            Fragment fragment = (Fragment) object;
            return fragment.getActivity();
        } else if (object instanceof View) {
            View view = (View) object;
            return view.getContext();
        }
        return null;
    }
}
