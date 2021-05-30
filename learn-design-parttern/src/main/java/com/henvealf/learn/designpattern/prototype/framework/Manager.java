package com.henvealf.learn.designpattern.prototype.framework;

import java.util.Hashtable;

public class Manager {	
	private Hashtable showcase = new Hashtable();
	public void register(String name, Product photo){
		showcase.put(name, photo);
	}
	public Product create(String protoname){
		Product p = (Product) showcase.get(protoname);
		return p.createClone();
	}
}
