package com.henvealf.learn.designpattern.builder;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		if(args.length != 1){
			usage();
			System.exit(0);
		}
		if(args[0].equals("plain")){
			Director diractor = new Director(new TextBuilder());
			String result = (String) diractor.constract();
			System.out.println(result);
		}
		else if(args[0].equals("html")){
			Director director = new Director(new HTMLBuilder());
			String fileName = (String) director.constract();
			System.out.println("易产生"+ fileName+ ".");
		}	else{
			usage();
			System.exit(0);
		}
		
		Director d = new Director(new FrameBuilder());
		JFrame frame = (JFrame) d.constract();
		frame.setVisible(true);
	}
	public static void usage(){
		System.out.println("shuo ming");
		System.out.println("shuo ming");
	}
}
