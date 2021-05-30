package com.henvealf.learn.designpattern.iterator;
/**
 * 
 * @author Henvealf
 *
 */
public interface Iterator {
	/**
	 * 查看下一个元素是否存在
	 * @return 下一个元素是否存在
	 */
	public abstract boolean hasNext();
	/**
	 * 返回聚合的下一个元素
	 * @return
	 */
	public abstract Object next();
}
