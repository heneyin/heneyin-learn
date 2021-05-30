package com.henvealf.learn.designpattern.singleton.practice.one;

public class TicketMaker {
	private int ticket = 100;
	private static TicketMaker singleton = new TicketMaker();
	private TicketMaker(){
	}
	
	public static TicketMaker getInstance(){
		return singleton;
	}
	public int getNextTicketNumber(){
		return ticket++;
	}
}
