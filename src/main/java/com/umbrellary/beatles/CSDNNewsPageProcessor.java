package com.umbrellary.beatles;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class CSDNNewsPageProcessor implements PageProcessor {

	// \\d+匹配页码数字
	public static final String URL_LIST = "/news/\\d+";

	// \\d+匹配页码数字 \\w+匹配任意字符串
	public static final String URL_POST = "http://www\\.csdn\\.net/article/\\d+-\\d+-\\d+/\\w+";

	private Site site = Site.me().setDomain("news.csdn.net").setSleepTime(3000).setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

	@Override
	public void process(Page page) {

		// 列表页
		if (page.getUrl().regex(URL_LIST).match()) {
			// page.putField("htmlURL_LIST",
			// page.getHtml().xpath("//div[@class=\"news\"]").links().regex(URL_POST).all());
			page.addTargetRequests(page.getHtml().xpath("//div[@class=\"news\"]").links().regex(URL_POST).all());
			page.addTargetRequests(page.getHtml().xpath("//div[@class='page_nav']").links().regex(URL_LIST).all());
			// 文章页
		} else {
			// page.putField("htmlURL_POST",
			// page.getHtml().xpath("//h1[@class=\"title\"]").toString());
			page.putField(page.getHtml().xpath("//h1[@class=\"title\"]").toString(), page.getHtml().xpath("//div[@class='con news_content']"));
		}

	}

	@Override
	public Site getSite() {
		return site;
	}

}
