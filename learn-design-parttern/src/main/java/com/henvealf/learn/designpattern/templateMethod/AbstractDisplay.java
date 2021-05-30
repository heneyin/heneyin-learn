package com.henvealf.learn.designpattern.templateMethod;

public abstract class AbstractDisplay {
	public abstract void open();
	public abstract void print();
	public abstract void close();
	public final void display(){
		open();   //��open()
		for(int i = 0; i < 5; i ++){
			print();
		}
		close();  
	}
}
/*��display��������Ϊ  final ���������ڴ˷������Լ̳е�������ȥʹ�ã������ܱ����า����д*/