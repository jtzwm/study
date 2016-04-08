package com.zhuwm.redis;

import com.zhuwm.redis.jedis.JedisUtil;

import redis.clients.jedis.Jedis;

public class OnLineUserImpl {
	
	
	private static String B_S_ONLINEUSERQUENE_NAME="k_ol_u_name";
	
	private Jedis jedis =JedisUtil.getJedis();
	
	public void putUserToQueue(String userId){
		jedis.lrem(OnLineUserImpl.B_S_ONLINEUSERQUENE_NAME, 0, userId);
		jedis.lpush(OnLineUserImpl.B_S_ONLINEUSERQUENE_NAME, userId);
	}
	
	public Long getQueueCount(){
		return jedis.llen(OnLineUserImpl.B_S_ONLINEUSERQUENE_NAME);
	}
	
	/**
	 * 返回最后一个队列
	 * @author zhuweiming
	 * @return
	 */
	public static String getLastUserFromQueue(){
		return "";
	}
	

}
