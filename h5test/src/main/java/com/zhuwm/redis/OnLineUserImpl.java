package com.zhuwm.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhuwm.redis.jedis.JedisUtil;

import redis.clients.jedis.Jedis;
/**
 * 在redis中保存在线用户列表的封装类。
 * 可获取在线用户数、向队列中增加用户、从队列中取出用户
 * 开发人员: @author zhuweiming<br>
 * 开发时间: 2016年4月8日<br>
 */
public class  OnLineUserImpl{
	
	private static Map<String,IOnLineUserObserver> onLineUserObserverMap = new HashMap<String,IOnLineUserObserver>();
	//初始化创建一个监听器
	static{
		onLineUserObserverMap.put("WebOnLineUserObserver",new WebOnLineUserObserver());
	}
	
	/**
	 * 在线用户列表的redis常量
	 */
	public static String B_S_ONLINEUSERQUENE_NAME="k_ol_u_name";
	/**
	 * 用于保存session id所对应的userId。
	 */
	public static String B_S_ONLINEUSERQUENE_SESSION_NAME=B_S_ONLINEUSERQUENE_NAME+":session_id:";
	
	private Jedis jedis =JedisUtil.getJedis();
	
	/**
	 * 保存sessionId所对应的userId。key为sessionId,value为userId
	 * 在websocket各事件中，通过sessionId，可以知道userId
	 * @author zhuweiming
	 * @param userId
	 * @param sessionId
	 */
	public void putSessionId(String sessionId,String userId){
		jedis.set(B_S_ONLINEUSERQUENE_SESSION_NAME+sessionId, userId);
	}
	
	/**
	 * 通过sessionId，获取userId
	 * @author zhuweiming
	 * @param sessionId
	 */
	public String getUserId(String sessionId){
		return jedis.get(B_S_ONLINEUSERQUENE_SESSION_NAME+sessionId);
	}
	
	/**
	 * 向队列中加入用户。
	 * @author zhuweiming
	 * @param userId
	 */
	public void putUserToQueue(String userId){
		jedis.lrem(OnLineUserImpl.B_S_ONLINEUSERQUENE_NAME, 0, userId);
		jedis.lpush(OnLineUserImpl.B_S_ONLINEUSERQUENE_NAME, userId);
		
		
		for (String key:onLineUserObserverMap.keySet()) {
			
			IOnLineUserObserver observer=(IOnLineUserObserver)onLineUserObserverMap.get(key);
			observer.onLineNotice();
		}
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

	/**
	 * 加入其他监听器
	 */
	public static void registerObserver(String key,IOnLineUserObserver observer) {		
		System.out.println("OnLineUserImpl:注册监听器数量："+onLineUserObserverMap.size());
		if(onLineUserObserverMap.get(key)!=null){
			onLineUserObserverMap.put(key, observer);
		}
	}


	

}
