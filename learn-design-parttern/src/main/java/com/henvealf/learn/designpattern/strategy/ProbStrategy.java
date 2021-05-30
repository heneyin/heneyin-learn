package com.henvealf.learn.designpattern.strategy;

import java.util.Random;
/**
 * 比较像样的“策略”
 * @author Henvealf
 *
 */
public class ProbStrategy implements Strategy{

	private Random random;
	private int prevHandValue = 0;
	private int currentHandValue = 0;
	//过去输赢记录的表格【上一次的手势】【这次的手势】
	private int[][] history={
		{1,1,1},
		{1,1,1},
		{1,1,1},
	};
	
	public ProbStrategy(int seed) {
		// TODO Auto-generated constructor stub
		random = new Random(seed);
	}
	
	@Override
	public Hand nextHand() {
		// TODO Auto-generated method stub
		int bet = random.nextInt(getSum(currentHandValue));
		int handValue = 0;
		
		if(bet < history[currentHandValue][0]){
			handValue = 0;
		}else if(bet < history[currentHandValue][0] + history[currentHandValue][1]){
			handValue = 1;
		}else{
			handValue = 2;
		}
		prevHandValue = currentHandValue;
		currentHandValue = handValue;
		return Hand.getHand(handValue);
	}

	@Override
	public void study(boolean win) {
		// TODO Auto-generated method stub
		if(win){
			history[prevHandValue][currentHandValue] ++;
		}else{
			history[prevHandValue][(currentHandValue + 1) % 3] ++;
			history[prevHandValue][(currentHandValue + 2) % 3] ++;
		}
	}
	
	private int getSum(int hv){
		int sum = 0;
		for(int i = 0; i< 3; i++){
			sum += history[hv][i];
		}
		return sum;
	}
	
}
