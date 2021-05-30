 package com.henvealf.learn.designpattern.iterator;

import java.util.Vector;

/**
 * 用来表现书架的类
 * @author Henvealf
 *
 */
public class BookShelf implements Aggregate{
	private Vector<Book> books;
	//private Book[] books;
	//private int last = 0;
	public BookShelf(int maxsize){
		this.books = new Vector<Book>(maxsize);
	}
	public Book getBookAt(int index){
		return (Book) books.get(index);
		//return books[index];
	}
	
	public void appendBook(Book book){
		books.add(book);
		//book[last] = book;
		//last++;
	}
	
	public int getLength(){
		return books.size();
	}
	
	public Iterator iterator(){
		return new BookShelfIterator(this);
	}

}
