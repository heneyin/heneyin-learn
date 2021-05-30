package com.henvealf.learn.designpattern.bridge;

/**
 * 桥接模式，实现与抽象解耦
 *
 */
public class Main {
	public static void main(String[] args) {

		Display dl = new Display(new StringDisplayImpl("heekdfklefje"));
		Display d2 = new Display(new StringDisplayImpl("adadadads"));
		CountDisplay d3 = new CountDisplay(new StringDisplayImpl("sdjsadhsajkdasjkdajsksdhsa"));
		dl.diaplay();
		d2.diaplay();
		d3.diaplay();
		d3.mutiDiaplay(4);
	}
}
