package com.example.app;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyAop {

    @Before("execution(public * com.example.app.User.*(..))")
    public void onBefore(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        //获取全类名
        String className = signature.getDeclaringTypeName();
        //获取方法名
        String methodName = signature.getName();
        Log.e("AOP", "onBefore() " + className + " " + methodName);
    }
}
