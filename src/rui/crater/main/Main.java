package rui.crater.main;

import rui.crater.beatles.MyPipeline;
import rui.crater.beatles.WeiboPageProcessor;
import us.codecraft.webmagic.Spider;

public class Main {

	public static void main(String[] args) {
//		Spider.create(new SinaPageProcessor())//
//				.addUrl("http://blog.sina.com.cn/s/articlelist_1487828712_0_1.html")//
//				.addPipeline(new MyPipeline())//
//				.thread(5)//
//				.run();
		
//		Spider.create(new CSDNPageProcessor())//
//		.addUrl("http://blog.csdn.net/?&page=2")//
//		.addPipeline(new MyPipeline())//
//		.thread(5)//
//		.run();
		
		Spider.create(new WeiboPageProcessor())//
		.addUrl("http://weibo.cn/5137667136/follow?page=1")//
		.addPipeline(new MyPipeline())//
		.thread(10)//
		.run();
	}
}