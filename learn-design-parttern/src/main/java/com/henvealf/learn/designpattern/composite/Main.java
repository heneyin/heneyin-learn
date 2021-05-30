package com.henvealf.learn.designpattern.composite;
/**
 * 将容器与 内容一视同仁
 * @author Henvealf
 *
 */
public class Main {
	public static void main(String[] args) {
		try{
			System.out.println("--------");
			Directory rootdir = new Directory("root");
			Directory bindir = new Directory("bin");
			Directory tmpdir = new Directory("tmp");
			Directory usrdir = new Directory("usr");
			rootdir.add(bindir);
			rootdir.add(tmpdir);
			rootdir.add(usrdir);
			
			bindir.add(new File("vi",123));
			bindir.add(new File("latex",111));
			
			rootdir.printList();
		}catch(FileTreatmentException e){
			
		}
	}

}
