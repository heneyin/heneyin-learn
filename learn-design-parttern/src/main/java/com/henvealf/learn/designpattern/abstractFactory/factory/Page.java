package com.henvealf.learn.designpattern.abstractFactory.factory;

import java.io.*;
import java.util.Vector;

/**
 * 用抽象的方法表现整个HTML的页面的类。抽象的产品
 * @author Henvealf
 *
 */
public abstract class Page {
	protected String title;    //网页标题
	protected String author;   //作者
	protected Vector content = new Vector();   //存放组成网页的零件们
	
	public Page(String title, String author){
		this.title = title;
		this.author = author;
	}
	/**
	 * 将抽象零件放入抽象产品中
	 * @param item 抽象零件
	 */
	public void add(Item item){
		content.add(item);
	}
	
	/**
	 * 根据网页标题决定文件名后， 输出整个网页。其中网页中的的具体内容由Page的子类书写
	 */
	public void output(){
		try{
			String fileName = title + ".html";
			Writer writer = new FileWriter(fileName);
			writer.write(this.makeHTML());
			writer.close();
			System.out.println("已经产生" + fileName + ".");
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	public abstract String makeHTML();
}
