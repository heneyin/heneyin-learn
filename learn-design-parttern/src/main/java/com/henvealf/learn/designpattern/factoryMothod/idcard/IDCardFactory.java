package com.henvealf.learn.designpattern.factoryMothod.idcard;

import com.henvealf.learn.designpattern.factoryMothod.framework.Factory;
import com.henvealf.learn.designpattern.factoryMothod.framework.Product;

import java.util.HashMap;
import java.util.Map;


public class IDCardFactory extends Factory {

	private Map<String,Integer> owners = new HashMap<String,Integer>();
	private int number = 100;
	@Override
	protected Product createProduct(String owner) {
		return new IDCard(owner, number++);
	}

	@Override
	protected void registerProduct(Product product) {
		owners.put(((IDCard)product).getOwner(),((IDCard)product).getNumber());
	}
	
	public Map getOwners(){
		return owners;
	}
}
