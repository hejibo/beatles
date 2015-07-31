package rui.crater.main;

import rui.crater.beatles.MyPageProcessor;
import rui.crater.beatles.MyPipeline;
import us.codecraft.webmagic.Spider;

public class Main {

	public static void main(String[] args) {
		Spider.create(new MyPageProcessor())//
				.addUrl("http://blog.sina.com.cn/s/articlelist_1487828712_0_1.html")//
				.addPipeline(new MyPipeline())//
				.thread(5)//
				.run();
	}
}