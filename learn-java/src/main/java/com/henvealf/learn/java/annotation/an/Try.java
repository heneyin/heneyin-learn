
package com.henvealf.learn.java.annotation.an;


import java.lang.annotation.*;

/**
 * Created by hongliang.yin/Henvealf on 2017/7/22.
 */

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Try {

    

}
