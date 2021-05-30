package com.henvealf.learn.designpattern.abstractFactory.factory;

import java.util.Vector;

/**
 * 抽象类
 * 此类是用来收集Link或者是他自身 。
 * @author Henvealf
 *
 */
public abstract class Tray extends Item{
	protected Vector tray = new Vector();
	public Tray(String caption){
		super(caption);
	}
	
	/**
	 * 
	 * @param item 要收集的Item或者他的子类的实例
	 */
	public void add(Item item){
		tray.add(item); 
	}
}
