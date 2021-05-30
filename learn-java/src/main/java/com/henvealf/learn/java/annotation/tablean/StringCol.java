package com.henvealf.learn.java.annotation.tablean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hongliang.yin/Henvealf on 2017/7/22.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StringCol {

    // name
    String value() default "";

    int length();
}
