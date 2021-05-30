package com.henvealf.learn.designpattern.abstractFactory;


import com.henvealf.learn.designpattern.abstractFactory.factory.Factory;
import com.henvealf.learn.designpattern.abstractFactory.factory.Link;
import com.henvealf.learn.designpattern.abstractFactory.factory.Page;
import com.henvealf.learn.designpattern.abstractFactory.factory.Tray;

/**
 * 利用工厂将零件组合成产品。
 * 不过是利用抽象工厂生产的抽象零件，。然后再组装成抽象产品。
 * 所以这个类没有任何具体的零件，产品，和工厂
 * @author Henvealf
 *
 */
public class Main {
	public static void main(String[] args) {
		if(args.length != 1){
			System.out.println("参数有误");
			System.exit(0);
		}
		
		
		Factory factory = Factory.getFactory(args[0]);
		
		Link sinaNew = factory.createLink("新浪新闻", "http://news.sina.com.cn/");
		Link baiduNew = factory.createLink("百度新闻", "http://news.baidu.com/");
		Link baidu = factory.createLink("百度", "http://www.baidu.com/");
		Link baiduZhidao = factory.createLink("百度知道", "http://zhidao.baidu.com/");
		Link sougou = factory.createLink("搜狗", "https://www.sogou.com/");
		Link youku = factory.createLink("优酷", "http://www.youku.com/");
		
		Tray trayNews = factory.createTray("新闻");
		trayNews.add(sinaNew);
		trayNews.add(baiduNew);
		
		Tray trayBaidu = factory.createTray("Baidu");
		trayBaidu.add(baidu);
		trayBaidu.add(baiduZhidao);
		
		Tray traySearch = factory.createTray("搜索引擎");
		traySearch.add(trayBaidu);
		traySearch.add(sougou);
		traySearch.add(youku);
		
		Page page = factory.createPage("LinkPage", "Henvealf");
		page.add(trayNews);
		page.add(traySearch);
		page.output();
	}
}
