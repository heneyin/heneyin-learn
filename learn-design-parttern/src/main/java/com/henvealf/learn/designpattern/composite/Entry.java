package com.henvealf.learn.designpattern.composite;
/**
 *对File和Directory一视同仁的类；表示目录进入点
 * @author Henvealf
 *
 */
public abstract class Entry {
	public abstract String getName();
	public abstract int getSize();
	public Entry add(Entry entry) throws FileTreatmentException{
		throw new FileTreatmentException();
	}
	public void printList(){
		printList("");
	}
	protected abstract void printList(String prefix);
	
	public String toString(){
		return getName() + "(" + getSize() + ")";
	}
}
