package com.henvealf.learn.designpattern.builder;

import java.io.FileWriter;
import java.io.PrintWriter;

public class HTMLBuilder extends Builder{
	private String fileName;
	private PrintWriter writer;
	@Override
	public void makeTitle(String title) {
		// TODO Auto-generated method stub
		fileName = title+".html";
		try {
			writer = new PrintWriter(new FileWriter(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		writer.println("<html><head><title>"+title+"</title></head></body>");
		writer.println("<h1>"+title+"</h1>");
		
	}

	@Override
	public void makeString(String str) {
		// TODO Auto-generated method stub
		writer.println("<p>" + str + "</p>");
	}

	@Override
	public void makeItems(String[] items) {
		writer.println("<ul>");
		for(int i = 0; i < items.length; i++){
			writer.println("<li>" + items[i] + "</li>");
		}
		writer.println("</ul>");
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		writer.println("</body></html>");
		writer.close();
		return fileName;
	}

}
