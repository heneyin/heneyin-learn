package com.henvealf.learn.java.annotation.tablean;

import java.lang.annotation.*;

/**
 * Created by hongliang.yin/Henvealf on 2017/7/22.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IntegerCol {

    // name
    String value() default "";

}
