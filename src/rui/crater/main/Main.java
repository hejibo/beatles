package rui.crater.main;

import rui.crater.beatles.CSDNNewsPageProcessor;
import rui.crater.beatles.MyPipeline;
import us.codecraft.webmagic.Spider;

public class Main {

	public static void main(String[] args) {

//		Spider.create(new CSDNBlogPageProcessor())//
//				.addUrl("http://blog.csdn.net/?&page=2")//
//				.addPipeline(new MyPipeline())//
//				.thread(5)//
//				.run();
		
		Spider.create(new CSDNNewsPageProcessor())//
		.addUrl("http://news.csdn.net/news/2")//
		.addPipeline(new MyPipeline())//
		.thread(5)//
		.run();

	}
}