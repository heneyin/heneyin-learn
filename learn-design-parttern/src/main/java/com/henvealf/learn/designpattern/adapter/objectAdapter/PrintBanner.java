package com.henvealf.learn.designpattern.adapter.objectAdapter;
/**
 * 对象Adapter Pattern 委托
 * @author Henvealf
 *
 */
public class PrintBanner extends Print{
	private Banner banner;
	public PrintBanner(String string) {
		this.banner = new Banner(string);
	}
	public void printWeak(){
		banner.showWithParen();
	}
	
	public void printStrong(){
		banner.showWithAster();
	}
	

}
