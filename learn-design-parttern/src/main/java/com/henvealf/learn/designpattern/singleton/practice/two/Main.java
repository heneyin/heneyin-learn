package com.henvealf.learn.designpattern.singleton.practice.two;

public class Main {
	public static void main(String[] args) {
		for(int i = 0; i < 9; i ++){
			Triple t = Triple.getInstace(i%3);
			System.out.println(t);
		}
	}
}
