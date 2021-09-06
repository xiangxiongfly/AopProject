package com.example.app;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class MyAop {

//    @Pointcut("execution(public * com.example.app.User.*(..))")
//    public void method() {
//    }

//    @Pointcut("execution(public * com.example.app.User.eat(..))")
//    public void eatMethod() {
//    }

//    @Before("method()")
//    public void onBefore(JoinPoint joinPoint) {
//        Signature signature = joinPoint.getSignature();
//        //获取全类名
//        String className = signature.getDeclaringTypeName();
//        //获取方法名
//        String methodName = signature.getName();
//        Log.e("AOP", "onBefore(): " + className + "#" + methodName);
//    }
//
//    @After("method()")
//    public void onAfter(JoinPoint joinPoint) {
//        Signature signature = joinPoint.getSignature();
//        String className = signature.getDeclaringTypeName();
//        String methodName = signature.getName();
//        Log.e("AOP", "onAfter(): " + className + "#" + methodName);
//    }

    /**
     * 注意不要使用try-catch
     */
//    @Around("eatMethod()")
//    public void onAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        Signature signature = joinPoint.getSignature();
//        //获取全类名
//        String className = signature.getDeclaringTypeName();
//        //获取方法名
//        String methodName = signature.getName();
//        Log.e("AOP", "onAround-start: " + className + "#" + methodName);
//        joinPoint.proceed();
//        Log.e("AOP", "onAround-end");
//    }

    @AfterReturning(pointcut = "execution(public String com.example.app.User.run(..))", returning = "returnValue")
    public void onAfterReturning(JoinPoint joinPoint, String returnValue) {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = signature.getParameterNames();
        for (int i = 0; i < parameterNames.length; i++) {
            Log.e("AOP", "参数 " + parameterNames[i] + ":" + args[i]);
        }
        Log.e("AOP", "onAfterReturning(): 方法名：" + signature.getName() + "，返回值：" + returnValue);
    }
}
