package rui.crater.config;

public class SysConfig {

	/*
	 * 数据库路径前缀
	 */
	public static final String urlIndex = "";
	public static final String DriverName = "com.mysql.jdbc.Driver";
	
	/* BAE */
//	public static String URL = "jdbc:mysql://sqld.duapp.com:4050/nYPeUBYbLVliksysJpOd";
//	public static String API_KEY = "vsFFLycSsPwA3cveXdwKjj85";
//	public static String SECERT_KEY = "X11agZ5ieTjEGLhiRBbtmhaVXUGXqLPY";

	/* 本地 */
	 public static String URL = "jdbc:mysql://127.0.0.1:3306/htmldata";
	 public static String API_KEY = "root";
	 public static String SECERT_KEY = "linuxos";
	 
	/* 远程 */
//	public static String URL = "jdbc:mysql://120.25.240.208:3306/jssvc";
//	public static String API_KEY = "root";
//	public static String SECERT_KEY = "linuxos";
	
	/*
	 * 数据库连接池配置
	 */
	public static final int setMaxPoolSize = 15;/* 最小连接数 */
	public static final int setMinPoolSize = 5;/* 最大连接数 */
	public static final int setAcquireIncrement = 5;/* 当连接池中的连接耗尽的时候c3p0一次同时获取的连接数 */
	public static final int setInitialPoolSize = 5;/* 初始化时获取三个连接，取值应在minPoolSize与maxPoolSize之间 */
	public static final int setMaxIdleTime = 240;/* 最大空闲时间,60秒内未使用则连接被丢弃(若为0则永不丢弃 )*/
	
	/*
	 * MemCache配置
	 */
	public static final int InitConn = 10;/* 初始连接数 */
	public static final int MinConn = 10;/* 最小连接数 */
	public static final int MaxConn = 1000;/* 最大连接数 */
	public static final int MaxIdle = 1000 * 60 * 60;/* 最大处理时间 */
	
	/*
	 * 客户端token超时时间
	 */
	public static final int TokenMaxIdle = 12 * 60 * 60 * 1000;/* 客户端token超时时间 */
	public static final String TokenPassword = "jssvcbyweichuangapps";/* DES加密密码 */
	
}
