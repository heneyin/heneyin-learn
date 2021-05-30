package com.henvealf.learn.designpattern.strategy;

public class Main {
	public static void main(String[] args) {
		Player p1 = new Player("????", new WinningStrategy(3));
		Player p2 = new Player("????", new ProbStrategy(5));
		for(int i = 0; i < 10000; i++){
			Hand nextHand1 = p1.nextHand();
			Hand nextHand2 = p2.nextHand();
			if(nextHand1.isStrongerThan(nextHand2)){
				System.out.println("Winer: " + p1);
				p1.win();
				p2.lose();
			}else if(nextHand1.isWeakerThan(nextHand2)){
				System.out.println("Winer: " + p2);
				p2.win();
				p1.lose();
			}
			else{
				System.out.println("Even...");
				p1.even();
				p2.even();
			}
		}
	}

}
