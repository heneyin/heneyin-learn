package com.henvealf.learn.designpattern.factoryMothod.framework;
/**
 * 产品的生产过程分为制作（create）和注册（register）；需要Factory的子类去定义细节；
 * 
 * @author Henvealf
 *
 */
public abstract class Factory {

	public final Product create(String owner){
		Product p = createProduct(owner);
		registerProduct(p);
		return p;
	}
	
	protected abstract Product createProduct(String owner);
	protected abstract void registerProduct(Product product);
}
