package com.henvealf.learn.designpattern.abstractFactory.listfactory;


import com.henvealf.learn.designpattern.abstractFactory.factory.Factory;
import com.henvealf.learn.designpattern.abstractFactory.factory.Link;
import com.henvealf.learn.designpattern.abstractFactory.factory.Page;
import com.henvealf.learn.designpattern.abstractFactory.factory.Tray;

public class ListFactory extends Factory {

	@Override
	public Link createLink(String caption, String url) {
		// TODO Auto-generated method stub
		return new ListLink(caption,url);
	}

	@Override
	public Tray createTray(String caption) {
		// TODO Auto-generated method stub
		return new ListTray(caption);
	}

	@Override
	public Page createPage(String title, String author) {
		// TODO Auto-generated method stub
		return new ListPage(title, author);
	}

}
