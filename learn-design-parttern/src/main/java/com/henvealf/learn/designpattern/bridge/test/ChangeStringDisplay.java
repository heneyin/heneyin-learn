package com.henvealf.learn.designpattern.bridge.test;

public class ChangeStringDisplay extends DisplayImpl{

	private String start;
	private String middle;
	private String end;	
	public ChangeStringDisplay(String start,String middle, String end) {
		this.start = start;
		this.middle = middle;
		this.end = end;
	}
	@Override
	public void rawStart() {
		System.out.print(start);
	}

	@Override
	public void rawMiddle() {
		System.out.print(middle);
	}

	@Override
	public void rawEnd() {
		System.out.println(end);
	}
	

}
