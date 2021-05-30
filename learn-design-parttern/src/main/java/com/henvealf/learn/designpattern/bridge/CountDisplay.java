package com.henvealf.learn.designpattern.bridge;

public class CountDisplay extends Display{

	public CountDisplay(DisplayImpl impl ){
		super(impl);
	}
	
	public void mutiDiaplay(int times){
		open();
		for(int i = 0; i <= times; i++){
			print();
		}
		close();
	}


}
