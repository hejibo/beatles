package rui.crater.tool;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库类
 * 
 * @author ZRUI
 *
 */
public class DBHelper {

	java.sql.Connection con = null;
	java.sql.Statement sqlStatement = null;
	java.sql.ResultSet rs = null;
	java.sql.PreparedStatement preparedStatement;

	public DBHelper() {
		try {
			con = MDBManager.getConnection();
			sqlStatement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 2014年11月29日 张瑞
	 * 
	 * @return 获取连接
	 */
	public java.sql.Connection getConnect() {
		return this.con;
	}

	/**
	 * 2014年11月29日 张瑞
	 * 
	 * @param sql
	 *            要执行的SQL语句
	 * @return 返回对象本身
	 */
	public java.sql.PreparedStatement prepareStatement(String sql) {
		try {
			preparedStatement = con.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preparedStatement;
	}

	/**
	 * 2014年11月29日 张瑞
	 * 
	 * @param sql
	 *            要执行的SQL语句
	 * @return 执行带返回值的SQL语句并返回一个表
	 */
	public ResultSet exeSQLreturnTable(String sql) {

		try {
			rs = sqlStatement.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return rs;
	}

	/**
	 * 2014年11月29日 张瑞
	 * 
	 * @param sql
	 *            要执行的SQL语句
	 * @return 返回受影响的行数
	 */
	public int exeSQLreturnInt(String sql) {
		int rs;
		try {
			rs = sqlStatement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return rs;
	}

	/**
	 * 2014年11月29日 张瑞
	 * 
	 * @return 关闭
	 */
	public void close() {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (preparedStatement != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (sqlStatement != null) {
			try {
				sqlStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
