package com.example.mylibrary.network;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CheckNet {
    int SHOW_TIP = 1;
    int SHOW_DIALOG = 2;

    int value() default SHOW_TIP;
}
