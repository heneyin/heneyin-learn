package com.henvealf.learn.designpattern.factoryMothod.idcard;

import com.henvealf.learn.designpattern.factoryMothod.framework.Product;

public class IDCard extends Product {

	private String owner;
	private int number;
	IDCard(String owner,int number){
		System.out.println("建立" + owner +"("+ number +")" + "的卡");
		this.owner = owner;
		this.number = number;
	}
	
	@Override
	public void use() {
		System.out.println("使用 " +owner+"("+ number +")" +"的卡");
	}
	public String getOwner(){
		return owner;
	}
	public int getNumber(){
		return number;
	}
	
}
/*练习：为身份证上添加号码*/