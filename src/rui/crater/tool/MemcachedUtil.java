package rui.crater.tool;

import java.util.Date;

import rui.crater.config.SysConfig;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MemcachedUtil {

	/**
	 * memcached客户端单例
	 */
	private static MemCachedClient cachedClient = new MemCachedClient();

	/**
	 * 初始化连接池
	 */
	static {
		// 获取连接池的实例
		SockIOPool pool = SockIOPool.getInstance();

		// 服务器列表及其权重
		String[] servers = { "127.0.0.1:11211" };
		Integer[] weights = { 3 };

		// 设置服务器信息
		pool.setServers(servers);
		pool.setWeights(weights);

		// 设置初始连接数、最小连接数、最大连接数、最大处理时间
		pool.setInitConn(SysConfig.InitConn);
		pool.setMinConn(SysConfig.MinConn);
		pool.setMaxConn(SysConfig.MaxConn);
		pool.setMaxIdle(SysConfig.MaxIdle);

		// 设置连接池守护线程的睡眠时间
		pool.setMaintSleep(60);

		// 设置TCP参数，连接超时
		pool.setNagle(false);
		pool.setSocketTO(60);
		pool.setSocketConnectTO(0);

		// 初始化并启动连接池
		pool.initialize();

		// 压缩设置，超过指定大小的都压缩
		// cachedClient.setCompressEnable(true);
		// cachedClient.setCompressThreshold(1024*1024);
	}

	public static boolean add(String key, Object value) {
		return cachedClient.add(key, value);
	}

	public static boolean add(String key, Object value, long expire) {
		Date date = new Date(expire);
		return cachedClient.add(key, value, date);
	}

	public static boolean set(String key, Object value) {
		return cachedClient.set(key, value);
	}

	/**
	 * 设置值到缓存
	 * 
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	public static boolean set(String key, Object value, long expire) {
		Date date = new Date(expire);
		return cachedClient.set(key, value, date);
	}

	public static boolean replace(String key, Object value) {
		return cachedClient.replace(key, value);
	}

	public static boolean replace(String key, Object value, Integer expire) {
		return cachedClient.replace(key, value, expire);
	}

	/**
	 * 根据ID获取缓存值
	 * 
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		return cachedClient.get(key);
	}

}
