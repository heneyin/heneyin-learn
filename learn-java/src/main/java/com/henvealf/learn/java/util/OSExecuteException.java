package com.henvealf.learn.java.util;

/**
 * 进程控制。这是一个定制的异常。
 * Created by Henvealf on 2016/9/3.
 */
public class OSExecuteException extends RuntimeException{
    public OSExecuteException(String why) {
        super(why);
    }
}
