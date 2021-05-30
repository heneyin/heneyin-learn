package com.henvealf.learn.designpattern.bridge.test;

public class Main {
	public static void main(String[] args) {
		Display c1 = new Display( new ChangeStringDisplay("<", "h", "-"));
		Display c2 = new Display( new ChangeStringDisplay("[", "3", "|"));;
		CountDisplay  c3 = new CountDisplay(new ChangeStringDisplay("[", "d", "]"));
		c1.display();
		c2.display();
		c3.countDisplay();
		c3.countDisplay();
		c3.countDisplay();
		for (int i = 0; i < 4; i++) {
			c3.countDisplay();
		}
	}
}
