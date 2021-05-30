package com.henvealf.learn.designpattern.singleton.practice.two;
/**
 * 一个对象实例数目只能有3个的类Triple
 * @author Henvealf
 *
 */
public class Triple {
	private int id;
	private static Triple[] tri = new Triple[]{
		new Triple(0),
		new Triple(1),
		new Triple(2)
	};
	
	private Triple(int id){
		this.id = id;
	}
	
	public static Triple getInstace( int id ){
		return tri[id];
	}
	@Override
	public String toString() {
		return "[Triple id=" + id + "]";
	}

}
