package com.henvealf.learn.designpattern.bridge.test;

public class Display {

	private DisplayImpl impl;
	public Display(DisplayImpl impl){
		this.impl = impl;
	}
	
	public void start(){
		impl.rawStart();
	}
	
	public void middle(){
		impl.rawMiddle();
	}
	
	public void end(){
		impl.rawEnd();
	}
	public void display(){
		start() ;
		middle();
		end();
	}
}
