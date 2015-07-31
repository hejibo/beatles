package rui.crater.tool;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import rui.crater.config.SysConfig;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据库连接池
 * 
 * @author ZRUI
 *
 */
public class MDBManager {
	private static final MDBManager instance = new MDBManager();
	private static ComboPooledDataSource cpds = new ComboPooledDataSource(true);

	static {
		cpds.setJdbcUrl(SysConfig.URL);
		try {
			cpds.setDriverClass(SysConfig.DriverName);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		cpds.setUser(SysConfig.API_KEY);
		cpds.setPassword(SysConfig.SECERT_KEY);
		cpds.setMaxPoolSize(SysConfig.setMaxPoolSize);
		cpds.setMinPoolSize(SysConfig.setMinPoolSize);
		cpds.setAcquireIncrement(SysConfig.setAcquireIncrement);
		cpds.setInitialPoolSize(SysConfig.setInitialPoolSize);
		cpds.setMaxIdleTime(SysConfig.setMaxIdleTime);
	}

	public static MDBManager getInstance() {
		return instance;
	}

	public static Connection getConnection() {
		try {
			return cpds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
