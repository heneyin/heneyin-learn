package com.henvealf.learn.java.annotation.an;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 一个会写程序的机器人
 * Created by hongliang.yin/Henvealf on 2017/7/22.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SmartRobot {

    String code() default "";

}

