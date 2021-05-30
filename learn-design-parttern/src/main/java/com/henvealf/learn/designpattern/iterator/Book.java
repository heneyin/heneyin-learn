package com.henvealf.learn.designpattern.iterator;
/**
 * 表示书籍的类
 * @author Henvealf
 *
 */
public class Book {
	private String name = "";
	public Book(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
}
