package com.henvealf.learn.designpattern.iterator;
/**
 * Aggregate 接口所声明的的方法只有iterator 方法一个，这是为了建立一个可对应聚合的Iterator,
 * 如果想递增，遍历或逐一检查某个集合时，利用iterator方法即可建立一个实现Iteraor接口的累对象实例（instance）
 * @author Henvealf
 *
 */
public interface Aggregate {
	public abstract Iterator iterator();
}
