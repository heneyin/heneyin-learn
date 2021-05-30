package com.henvealf.learn.designpattern.builder;

public class Director {
	private Builder builder;
	public Director(Builder builder){
		this.builder = builder;
	}
	public Object constract(){
		builder.makeTitle("greeting");
		builder.makeString("从早上到白天结束");
		builder.makeItems(new String[]{
				"早安","午安",
		});
		builder.makeString("dao le wan shang");
		builder.makeItems(new String[]{
				"shui jiao",
				"zhu ni wan an "
		});
		return builder.getResult();
	}
}
