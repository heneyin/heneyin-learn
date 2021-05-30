package com.henvealf.learn.designpattern.adapter.classAdapter;
/**
 * 发挥适配器的功能
 * @author Henvealf
 *
 */
public class PrintBanner extends Banner implements Print{
	public PrintBanner(String string){
		super(string);
	}

	@Override
	public void printWeak() {
		showWithParen();
	}

	@Override
	public void printStrong() {
		showWithAster();
	}
	
}
