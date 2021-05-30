package com.henvealf.learn.designpattern.templateMethod;
/**
 * ??巽?? -????????????
 * @author Henvealf
 *
 */
public class Main {

	public static void main(String[] args) {
		AbstractDisplay d1 = new CharDisplay('e');
		AbstractDisplay d2 = new StringDisplay("Henvealf");
		d1.display();
		d2.display();
	}
	
}
