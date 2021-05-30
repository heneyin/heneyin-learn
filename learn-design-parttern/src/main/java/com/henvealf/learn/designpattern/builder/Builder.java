package com.henvealf.learn.designpattern.builder;
/**
 * 声明产生文件的所有方法的抽象类
 * @author Henvealf
 *
 */
public abstract class Builder {
	public abstract void makeTitle(String title);
	public abstract void makeString(String str);
	public abstract void makeItems(String[] items);
	public abstract Object getResult();
}
