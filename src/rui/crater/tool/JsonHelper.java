package rui.crater.tool;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Zrui(rui.crater@gmail.com)
 *
 *         2015年1月26日
 */
public class JsonHelper {

	JSONObject jsonobjectmain;
	JSONObject jsonobjectcontent;
	JSONArray arraymain;

	/**
	 * 构造方法
	 */
	public JsonHelper() {
		this.jsonobjectcontent = new JSONObject();
		this.jsonobjectmain = new JSONObject();
		this.arraymain = new JSONArray();
	}

	/**
	 * 添加字段
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public JsonHelper addcontent(String key, String value) {
		jsonobjectcontent.put(key, value);
		return this;
	}

	/**
	 * 结束添加一行
	 * 
	 * @param jsonname
	 * @return
	 */
	public JsonHelper finish(String jsonname) {
		JSONArray arrayline = new JSONArray();
		arrayline.put(this.jsonobjectcontent);
		this.jsonobjectmain.put(jsonname, arrayline);
		jsonobjectcontent = new JSONObject();
		return this;
	}

	/**
	 * 获取最终结果
	 * 
	 * @return
	 */
	public String getJsonString() {
		return this.jsonobjectmain.toString();
	}

	/**
	 * 添加返回代码
	 * 
	 * @param errorno
	 * @param msg
	 * @return
	 */
	public JsonHelper setReturnCode(String errorno, String msg) {
		this.jsonobjectmain.put("returncode", errorno);
		this.jsonobjectmain.put("message", msg);
		return this;
	}

	/**
	 * 将结果集插入到数据区域
	 * 
	 * @param rs
	 * @param jsonname
	 * @return
	 */
	public JsonHelper addResultSet(ResultSet rs, String jsonname) {

		JSONArray array = new JSONArray();
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				JSONObject jsonObj = new JSONObject();

				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnLabel(i);
					String value = rs.getString(columnName);
					jsonObj.put(columnName, value);
				}
				array.put(jsonObj);
			}
			this.jsonobjectmain.put(jsonname, array);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this;
	}
}
