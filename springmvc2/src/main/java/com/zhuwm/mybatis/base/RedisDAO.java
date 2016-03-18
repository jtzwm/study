package com.zhuwm.mybatis.base;

import com.zhuwm.jedis.RedisUtil;

import redis.clients.jedis.Jedis;

/**
 * 封装对Redis的操作
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author zhuweiming<br>
 * 开发时间: 2016年2月25日<br>
 */
public class RedisDAO {

	protected Jedis jedis;

	public RedisDAO() {
		jedis = RedisUtil.getJedis();
	}
	/**
	 * 释放jedis连接，需要子类自行调用此方法释放
	 * @author zhuweiming
	 */
	public void returnResource() {
		RedisUtil.returnResource(jedis);
	}

	/**
	 * 从Redis获取
	 * @author zhuweiming
	 * @return
	 */
	public Object getObjectFromRedis(String key) {
		if (key == null) 
			return null;
		return jedis.get(key);
	}
	
	/**
	 * 创建key
	 * @author zhuweiming
	 * @param key
	 */
	public String createKey(String key,String value){
		if (key == null) 
			return null;
		return jedis.set(key, value);
	}
	
	/**
	 * 设置key的过期时间
	 * @author zhuweiming
	 * @param key
	 * @param expiredTime
	 * @return
	 */
	public String createKeyAndExpiredTime(String key,int expiredTime,String value){
		return jedis.setex(key, expiredTime, value);
		
	}
	
	/**
	 * 用来管理数据库主键，如果数据库中没有，则初始化为0，并自增1。如果有，则返回ID并自增1.
	 * @author zhuweiming
	 * @param key
	 * @return
	 */
	public String getIDAndIncr(String key){
		
		String result=jedis.get(key);
		if(result != null)
			jedis.incr(key);
		else{
			jedis.set(key,"1");
			result ="1";
			
		}
		return result;
	}

}
