package com.example.mylibrary.click;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class SingleClickAspect {
    private long lastTime;
    private String lastTag;

    @Pointcut("execution(@com.example.mylibrary.click.SingleClick * *(..))")
    public void method() {
    }

    @Around("method() && @annotation(singleClick)")
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint, SingleClick singleClick) throws Throwable {
        //获取被注解方法的签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获取类名
        String className = methodSignature.getDeclaringType().getName();
        //获取方法名
        String methodName = methodSignature.getName();

        //创建TAG
        StringBuilder builder = new StringBuilder(className + "." + methodName);
        builder.append("(");
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (i == 0) {
                builder.append(arg);
            } else {
                builder.append(",").append(arg);
            }
        }
        builder.append(")");
        String tag = builder.toString();

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime < singleClick.value() && tag.equals(lastTag)) {
            Log.e("TAG", "invalid");
            return;
        }
        lastTime = currentTime;
        lastTag = tag;
        joinPoint.proceed();
    }
}
