package com.henvealf.learn.java.jmx;

import java.util.List;

/**
 * @author hongliang.yin/Henvealf on 2018/9/27
 */
public interface HelloJmxMBean {

    public String getName();

    public void setName(String name);

    void printName();

//    public int getAge() ;
//    public void setAge(int age) ;
    public List<String> getHobby() ;

    public void setHobby(List<String> hobby) ;

    void printHobby();
}
