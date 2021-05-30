package com.henvealf.learn.designpattern.abstractFactory.factory;
/**
 * 抽象的工厂，生产抽象的产品
 * @author Henvealf
 *
 */
public abstract class Factory {
	/**
	 * 指定类名的方式声明具体工厂的实例
	 * @param className 工厂的类名
	 * @return
	 */
	public static Factory getFactory(String className){
		Factory factory = null ;
		try {
			factory = (Factory) Class.forName(className).newInstance();
		} catch (ClassNotFoundException e) {
			System.out.println("找不到类" +  className +". ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return factory;
	}
	
	//生产零件的过程，具体的制作过程交给抽象Factory的子类进行具体处理
	public abstract Link createLink(String caption, String url);
	public abstract Tray createTray(String caption);
	public abstract Page createPage(String title, String author);
}
