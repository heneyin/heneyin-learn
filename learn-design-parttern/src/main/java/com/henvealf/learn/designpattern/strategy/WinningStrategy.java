package com.henvealf.learn.designpattern.strategy;

import java.util.Random;

public class WinningStrategy implements Strategy{

	private Random random = new Random();
	private boolean won = false;
	private Hand prevHand;
	public  WinningStrategy(int seed) {
		random = new Random(seed);
	}
	
	@Override
	public Hand nextHand() {
		// TODO Auto-generated method stub
		//如果赢了，就使用本次获胜的手势作为下一次出的手势
		//如果输了，就随机获得一个手势
		if(!won){
			prevHand = Hand.getHand(random.nextInt(3));
		}
		return prevHand;
	}

	@Override
	public void study(boolean win) {
		// TODO Auto-generated method stub
		won = win;
	}

}
