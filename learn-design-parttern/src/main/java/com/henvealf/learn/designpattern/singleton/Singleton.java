package com.henvealf.learn.designpattern.singleton;
/**
 * 当程序开始执行后，第一次调用getInstance（）方法是才会初始化Singleton类。
 * 同时也会初始化static字段，产生唯一的对象实例
 * @author Henvealf
 *
 */
public class Singleton {
	private static Singleton singleton  = new Singleton();
	private Singleton(){
		System.out.println("已经产生对象实例");
	}
	public static Singleton getInstance(){
		return singleton;
	}
}

