package com.henvealf.learn.designpattern.bridge;

/**
 * 功能的类层次
 * @author Henvealf
 *
 */
public class Display {

	private DisplayImpl impl;
	public Display(DisplayImpl impl){
		this.impl = impl;
	}
	public void open(){   //打印前的处理
		impl.rawOpen();
	}
	public void print(){   //打印行为
		impl.rawPrint();
	}
	public void close(){ //打印后的处理
		impl.rawClose();   
	}
	public final void diaplay(){
		open();
		print();
		close();
	}

}
