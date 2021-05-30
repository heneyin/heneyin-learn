package com.henvealf.learn.designpattern.composite;
/**
 * 表示文件的类
 * @author Henvealf
 *
 */
public class File extends Entry{

	private String name;
	private int size;
	
	public File(String name, int size){
		this.name = name;
		this.size = size;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	protected void printList(String prefix) {
		// TODO Auto-generated method stub
		System.out.println(prefix + "/" + this);
	}

}
