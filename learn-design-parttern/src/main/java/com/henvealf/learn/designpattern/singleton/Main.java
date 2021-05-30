package com.henvealf.learn.designpattern.singleton;

public class Main {
	public static void main(String[] args) {
		System.out.println("start");
		Singleton obj1 = Singleton.getInstance();
		Singleton obj2 = Singleton.getInstance();
		if(obj1 == obj2){
			System.out.println("两个是同一个对象");
		}
		else{
			System.out.println("两个不是同一个人对象");
		}
		System.out.println("End");
	}
}
