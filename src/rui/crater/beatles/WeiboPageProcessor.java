package rui.crater.beatles;

import java.sql.ResultSet;
import java.sql.SQLException;

import rui.crater.tool.DBHelper;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class WeiboPageProcessor implements PageProcessor {

	public static final String URL_MOREUSER = "http://weibo\\.cn/\\d+/follow\\?page=\\d+";// 关注的用户列表

	public static final String URL_MOREUSERPAGE = "/\\d+/follow\\?page=\\d+";// 关注的用户列表分页
	public static final String URL_USER = "http://weibo\\.cn/u/\\d+";// 用户首页

	public static final String URL_FOLLOW = "/\\d+/follow";// 关注的用户列表分页
	public static final String URL_FANS = "/\\d+/fans";// 关注的用户列表分页
	public static final String URL_INFOPAGE = "/\\d+/info";// 用户信息页面

	public static final String URL_INFO = "http://weibo\\.cn/\\d+/info";// 用户信息页

	public static final String URL_FOLLOWNEXT = "http://weibo\\.cn/\\d+/follow";// 用户首页
	public static final String URL_FANSNEXT = "http://weibo\\.cn/\\d+/fans";// 用户首页

	private Site site = Site.me()//
			.setDomain("weibo.cn")//
			.setSleepTime(3000)//
			.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")//
			.addCookie(".weibo.cn", "_T_WM", "ad8526c820761467047279f2648bf75b")//
			.addCookie(".weibo.cn", "gsid_CTandWM", "4ueK6ecb1V0pxDDMUFEyLlyxB2c")//
			.addCookie(".weibo.cn", "SUB", "_2A254uPiDDeTxGeNP6FUX9inNyDqIHXVYQpjLrDV6PUJbrdAKLWfekW0JhuU1gWb_VWOMpi-3MLdtB-fvCw..");

	@Override
	public void process(Page page) {

		// page.putField("html", page.getUrl());

		/* 如果打开的是用户列表页 */
		if (page.getUrl().regex(URL_MOREUSER).match()) {

			page.addTargetRequests(page.getHtml().xpath("//td[@valign=\"top\"]").links().regex(URL_USER).all());// 添加用户首页链接
			page.addTargetRequests(page.getHtml().links().regex(URL_MOREUSERPAGE).all());// 添加用户列表下一页链接

			// page.putField("1---",
			// page.getHtml().xpath("//td[@valign=\"top\"]").links().regex(URL_USER).all());
			// page.putField("2---",
			// page.getHtml().links().regex(URL_MOREUSERPAGE).all());

			/* 如果打开的是用户主页 */
		} else if (page.getUrl().regex(URL_USER).match()) {

			/* 判断是否重复 */
			if (haveNoid(page.getUrl().get())) {

				page.addTargetRequests(page.getHtml().xpath("//div[@class=\"ut\"]/a").links().regex(URL_INFOPAGE).all());// 添加用户信息页链接
				page.addTargetRequests(page.getHtml().xpath("//div[@class=\"tip2\"]/a").links().regex(URL_FOLLOW).all());// 添加用户关注链接
				page.addTargetRequests(page.getHtml().xpath("//div[@class=\"tip2\"]/a").links().regex(URL_FANS).all());// 添加用户粉丝链接

				// page.putField("1---",
				// page.getHtml().xpath("//div[@class=\"ut\"]/a").links().regex(URL_INFOPAGE).all());
				// page.putField("2---",
				// page.getHtml().xpath("//div[@class=\"tip2\"]/a").links().regex(URL_FOLLOW).all());
				// page.putField("3---",
				// page.getHtml().xpath("//div[@class=\"tip2\"]/a").links().regex(URL_FANS).all());

			}

			/* 如果打开的是用户粉丝和关注页面 */
		} else if (page.getUrl().regex(URL_FOLLOWNEXT).match() || page.getUrl().regex(URL_FANSNEXT).match()) {

			page.addTargetRequests(page.getHtml().xpath("//td[@valign=\"top\"]/a").links().regex(URL_USER).all());// 添加粉丝和自己关注人员的主页（用来发现朋友的朋友）

			// page.putField("1---",
			// page.getHtml().xpath("//td[@valign=\"top\"]/a").links().regex(URL_USER).all());

			/* 如果打开的是用户信息页面 */
		} else if (page.getUrl().regex(URL_INFO).match()) {

			page.putField(page.getUrl().toString(), page.getHtml().toString());// 存储用户信息

		}
	}

	@Override
	public Site getSite() {
		return site;
	}

	/**
	 * 判断是否已经存储过此用户
	 * 
	 * @param key
	 * @return
	 */
	public boolean haveNoid(String key) {

		System.out.println("---------->" + key);

		DBHelper dbhelper = new DBHelper();

		try {
			java.sql.PreparedStatement preparestatement = dbhelper.prepareStatement("SELECT * FROM `myhash` WHERE `key`= ?");
			preparestatement.setString(1, key);
			ResultSet resultset = preparestatement.executeQuery();
			if (resultset.last() && resultset.getRow() > 0) {
				dbhelper.close();
				return false;
			} else {
				dbhelper.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			dbhelper.close();
			return false;
		}
	}

}
