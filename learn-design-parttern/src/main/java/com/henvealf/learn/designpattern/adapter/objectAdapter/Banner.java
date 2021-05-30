package com.henvealf.learn.designpattern.adapter.objectAdapter;
/**
 * 既有的类
 * @author Henvealf
 *
 */
public class Banner {
	public 	String string;
	public Banner(String string){
		this.string = string;
	}
	
	public void showWithParen(){
		System.out.println("(" + string + ")");
	}
	
	public void showWithAster(){
		System.out.println("*" + string + "*");
	}
}
