package com.henvealf.learn.designpattern.adapter.objectAdapter;
/**
 * 类的Adapter Pattern （继承）
 * @author Henvealf
 *
 */
public class Main {
	public static void main(String[] args) {
		Print p = new PrintBanner("hello");
		p.printStrong();
		p.printWeak();
	}
}
