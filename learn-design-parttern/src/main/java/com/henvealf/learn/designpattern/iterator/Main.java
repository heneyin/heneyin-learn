package com.henvealf.learn.designpattern.iterator;
/**
 * 迭代器
 * @author Henvealf
 *
 */
public class Main {
	public static void main(String[] args) {
		BookShelf bookShelf = new BookShelf(4);
		bookShelf.appendBook(new Book("《百年孤独》"));
		bookShelf.appendBook(new Book("《从你的世界里走过》"));
		bookShelf.appendBook(new Book("《挪威的森林》"));
		bookShelf.appendBook(new Book("《小王子》"));
		bookShelf.appendBook(new Book("《海底两万里》"));
		Iterator it = bookShelf.iterator();
		
		while(it.hasNext()){
			Book book = (Book) it.next();
			System.out.println("" + book.getName());
		}
	}
}
