package com.henvealf.learn.designpattern.strategy;
/**
 * 表示猜拳时手势的类
 * @author Henvealf
 *
 */
public class Hand {
	public static final int SHI = 0;  //石头
	public static final int JIAN = 1;  //剪刀
	public static final int BU = 2;  //布
	
	public static final Hand[] hand = {   //表示猜拳手势的三个类
		new Hand(SHI),
		new Hand(JIAN),
		new Hand(BU),
	};
	
	private static final String[] name = {   //猜拳手势的值
		"石头", "剪刀", "布"
	};
	
	private int handValue;
	
	private Hand(int h){
		this.handValue = h;
	}
	
	public static Hand getHand( int handValue){  //获取一个手势对象
		return hand[handValue];
	}
	
	public boolean isStrongerThan(Hand h){    //如果赢过就返回true
		return fight(h) == 1;
	}
	
	public boolean isWeakerThan(Hand h){
		return fight(h) == -1;
	}
	
	private int fight(Hand h){
		if (this == h){
			return -1;
		}else if((this.handValue + 1) % 3 == h.handValue){
			return 1;
		}else{
			return -1;
		}
	}
	
	public String toString(){
		return name[handValue];
	}
	
}
