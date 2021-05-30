package com.henvealf.learn.designpattern.adapter.practice;

import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		FileIO f = new FileProperties();
		try{
			f.readFromFile("D:\\file.txt");
			f.setValue("year", "2000");
			f.setValue("month", "21");
			f.setValue("hahah", "nizhibusd");
			f.writeToFile("D:\\newfile.txt");
			System.out.println(f.getValue("year"));
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
}
