package com.henvealf.learn.designpattern.strategy;
/**
 * 抓出所有 猜拳“策略”的接口；
 * @author Henvealf
 *
 */
public interface Strategy {
	/**
	 *  得到下一次要出的手势
	 * @return 下一次要出的招数
	 */
	public abstract Hand nextHand();
	/**
	 * 学习前一次出的手势有没有赢，如果nextHand的结果是Win或者Flase，实现接口的类就会根据自己的算法来修改自己的内部状态，决定下一次要出的手势 
	 * @param win
	 */
	public abstract void study(boolean win);
}
