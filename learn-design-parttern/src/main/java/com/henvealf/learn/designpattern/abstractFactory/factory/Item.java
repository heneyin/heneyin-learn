package com.henvealf.learn.designpattern.abstractFactory.factory;
/**
 * 抽象零件类。
 * @author Henvealf
 *
 */
public abstract class Item {
	protected String caption;    //一个小项目的标题
	public Item(String caption){
		this.caption = caption;
	}
	/**
	 * 
	 * @return HTML 的字符串
	 */
	public abstract String makeHTML();
}
