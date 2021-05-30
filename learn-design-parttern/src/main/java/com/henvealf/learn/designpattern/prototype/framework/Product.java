package com.henvealf.learn.designpattern.prototype.framework;
/**
 * 有了此接口才可以进行复制
 * @author Henvealf
 *
 */
public interface Product extends Cloneable{
	/**
	 * "使用部分"，采用何种方法使用，需要看子类的实现
	 * @param s 
	 */
	public abstract void use(String s);
	/**
	 * 负责复制对象实例部分
	 * @return
	 */
	public abstract Product createClone();
}
