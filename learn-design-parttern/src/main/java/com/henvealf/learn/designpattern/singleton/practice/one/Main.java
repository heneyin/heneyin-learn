package com.henvealf.learn.designpattern.singleton.practice.one;

public class Main {
	public static void main(String[] args) {
		TicketMaker t =TicketMaker.getInstance();
		for(int i = 0; i < 5; i ++){
			System.out.println(t.getNextTicketNumber());
		}
	}
}
