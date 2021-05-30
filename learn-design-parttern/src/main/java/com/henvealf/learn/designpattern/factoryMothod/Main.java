package com.henvealf.learn.designpattern.factoryMothod;


import com.henvealf.learn.designpattern.factoryMothod.framework.Factory;
import com.henvealf.learn.designpattern.factoryMothod.framework.Product;
import com.henvealf.learn.designpattern.factoryMothod.idcard.IDCardFactory;

public class Main {
	public static void main(String[] args) {
		Factory factory = new IDCardFactory();
		                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
		Product card1 = factory.create("hudis");
		Product card2 = factory.create("12313");
		Product card3 = factory.create("AASDFA");
		
		card1.use();
		card2.use();
		card3.use();
		
	}
}
