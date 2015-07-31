/**
 * 
 */
package rui.crater.tool;

/**
 * @author Zrui(rui.crater@gmail.com)
 *
 *         2015年2月9日
 */
public class SqlHelper {
	/**
	 * 校验是否存在SQL注入关键字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean sqlValidate(String str) {
		str = str.toLowerCase();
		String badStr = "'|--|\"";
		String[] badStrs = badStr.split("\\|");
		for (int i = 0; i < badStrs.length; i++) {
			String tempstr = badStrs[i];
			if (str.indexOf(tempstr) != -1) {
				return true;
			}
		}
		return false;
	}

}
