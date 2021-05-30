package com.henvealf.learn.designpattern.adapter.practice;

import java.io.IOException;
/**
 * target
 * @author Henvealf
 *
 */
public interface FileIO {
	public void readFromFile(String filename) throws IOException;
	public void writeToFile(String filename) throws IOException;
	public void setValue(String key, String value);
	public String getValue(String key);
}
