package com.zhuwm.redis;

import java.util.List;

import com.zhuwm.redis.jedis.JedisUtil;

import redis.clients.jedis.Jedis;
/**
 * 在redis中保存在线视频用户列表的封装类。
 * 可获取在线用户数、向队列中增加用户、从队列中取出用户
 * 开发人员: @author zhuweiming<br>
 * 开发时间: 2016年4月8日<br>
 */
public class  OnLineUserImpl {
	
	
	public static String B_S_ONLINEUSERQUENE_NAME="k_ol_u_name";
	
	private Jedis jedis =JedisUtil.getJedis();
	
	/**
	 * 向队列中加入用户。
	 * @author zhuweiming
	 * @param userId
	 */
	public void putUserToQueue(String userId){
		jedis.lrem(OnLineUserImpl.B_S_ONLINEUSERQUENE_NAME, 0, userId);
		jedis.lpush(OnLineUserImpl.B_S_ONLINEUSERQUENE_NAME, userId);
	}
	
	/**
	 * 获取队列中用户数。
	 * @author zhuweiming
	 * @return
	 */
	public Long getQueueCount(){
		return jedis.llen(OnLineUserImpl.B_S_ONLINEUSERQUENE_NAME);
	}
	
	public List<String> getQueueUserList(){
		return jedis.lrange(OnLineUserImpl.B_S_ONLINEUSERQUENE_NAME, 0, -1);
	}
	
	/**
	 * 从队列中取出最先进入的用户。
	 * @author zhuweiming
	 * @return
	 */
	public String getLastUserFromQueue(){
		return jedis.rpop(OnLineUserImpl.B_S_ONLINEUSERQUENE_NAME);		
	}
	
	/**
	 * 释放jedis资源
	 * @author zhuweiming
	 */
	public void releaseJedis(){
		JedisUtil.returnResource(jedis);
	}
	

}
