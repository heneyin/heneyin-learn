package com.henvealf.learn.java.annotation;



import com.henvealf.learn.java.annotation.an.LazyHuman;
import com.henvealf.learn.java.annotation.an.SmartRobot;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


/**
 * Created by hongliang.yin/Henvealf on 2017/7/22.
 */
public class Play {

    public static void humanVsRobot(Class systemClazz) {

        int humanCount = 0;
        int robotCount = 0;

        for(Method m : systemClazz.getDeclaredMethods()){

            System.out.println(m.getName());

            SmartRobot smartRobot = m.getAnnotation(SmartRobot.class);
            LazyHuman lazyHuman = m.getAnnotation(LazyHuman.class);

            if (smartRobot != null){
                robotCount ++;
            } else if(lazyHuman != null) {
                humanCount ++;
            }
        }

        System.out.println("human have: " + humanCount);
        System.out.println("robot have: " + robotCount);
    }

    public static void main(String[] args) {
        Play.humanVsRobot(BigSystem.class);
    }

}
