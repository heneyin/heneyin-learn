package com.henvealf.learn.designpattern.abstractFactory.factory;
/**
 * 以抽象的方式表示HTML中的超链接 的类； 
 * @author Henvealf
 *
 */
public abstract class Link extends Item{
	protected String url;   //储存网站链接地址的字段
	public Link(String caption,String url){
		super(caption);
		this.url = url;
	}
}
