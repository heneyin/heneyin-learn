package com.henvealf.learn.designpattern.command;

/**
 * 一个电视机，接受命令。
 * @author hongliang.yin/Henvealf on 2019/1/17
 */
public class Televation {

    private int status = 0;

    public void open(){
        status = 1;
        System.out.println("opened");
    }

    public void close() {
        status = 0;
        System.out.println("cloed");
    }

    public void switchStation(String stationName) {
        System.out.println("switch to " + stationName);
    }

}
