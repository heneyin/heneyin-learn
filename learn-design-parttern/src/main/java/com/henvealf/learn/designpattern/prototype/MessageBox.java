package com.henvealf.learn.designpattern.prototype;


import com.henvealf.learn.designpattern.prototype.framework.Product;

public class MessageBox implements Product {
	//边框字符
	private char decochar;
	
    public MessageBox(char decochar){
    	this.decochar = decochar;
    }
	/**
	 * 利用decochar将s框在里面
	 */
	@Override
	public void use(String s) {
		int length = s.getBytes().length;
		for(int i = 0; i < length; i++){
			System.out.print(decochar);
		}
		System.out.println("");
		System.out.println(decochar + " " + s + " " + decochar);
		for(int i = 0; i < length; i++){
			System.out.print(decochar);
		}
		System.out.println();
	}
	/**
	 * 复制本身
	 */
	@Override
	public Product createClone() {
		Product p = null;
		try {
			p = (Product)clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return p;
	}

}
