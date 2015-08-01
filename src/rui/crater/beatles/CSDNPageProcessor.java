package rui.crater.beatles;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class CSDNPageProcessor implements PageProcessor {

	public static final String URL_LIST = "/?&page=\\d+";// \\d+匹配页码数字

	public static final String URL_POST = "http://blog\\.csdn\\.net/\\w+/article/details/\\d+";// \\d+匹配页码数字
																								// \\w+匹配任意字符串

	private Site site = Site.me().setDomain("blog.csdn.net").setSleepTime(3000).setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

	@Override
	public void process(Page page) {
		// 列表页
		if (page.getUrl().regex(URL_LIST).match()) {
			// page.putField("htmlURL_LIST", page.getHtml());
			page.addTargetRequests(page.getHtml().xpath("//div[@class=\"blog_list\"]").links().regex(URL_POST).all());
			page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
			// 文章页
		} else {
			// page.putField("htmlURL_POST", page.getHtml());
			// page.putField("title",
			// page.getHtml().xpath("//div[@class='article_title']/h1/span/a/text()"));
			page.putField(page.getHtml().xpath("//div[@class='article_title']/h1/span/a/text()").toString(), page.getHtml().xpath("//div[@class='article_content']"));
		}
	}

	@Override
	public Site getSite() {
		return site;
	}

}
