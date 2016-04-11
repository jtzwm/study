package com.zhuwm.redis;

import com.zhuwm.redis.jedis.JedisUtil;

import redis.clients.jedis.Jedis;

/**
 * 封装等待进行视频见证的用户队列。队列在redis中存放。
 * 调用者不需要关心redis实现机制。
 * 
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author zhuweiming<br>
 * 开发时间: 2016年4月7日<br>
 */
public class VideoQueueImpl {
	
	/**
	 * Redis中视频队列的名称
	 */
	public static String B_S_VIDEOQUENE_NAME="k_v_q_name";
	
	private Jedis jedis =JedisUtil.getJedis();
	
	/**
	 * 将用户添加进待接通视频的队列
	 * @author zhuweiming
	 * @param userId
	 * @param roomId
	 */
	public void addUserToVideoQueue(String busKey,String roomId){
		jedis.lrem(VideoQueueImpl.B_S_VIDEOQUENE_NAME, 0, busKey);
		jedis.lpush(VideoQueueImpl.B_S_VIDEOQUENE_NAME, busKey);
		//同时保存房间号
		jedis.set(VideoQueueImpl.B_S_VIDEOQUENE_NAME+":room_id:"+busKey, roomId);
		
	}
	
	/**
	 * 从视频队列中接通一个视频，并从视频队列中删除
	 */
	public String popFromVideoQueue(){
		String temp= jedis.rpop(OnLineUserImpl.B_S_ONLINEUSERQUENE_NAME);
		jedis.del(VideoQueueImpl.B_S_VIDEOQUENE_NAME+":room_id:"+temp);
		return temp;		
	}
	
	/**
	 * 通过主键得到对应的房间号
	 * @author zhuweiming
	 * @param busKey
	 * @return
	 */
	public String getRoomIdByKey(String busKey){
		return jedis.get(VideoQueueImpl.B_S_VIDEOQUENE_NAME+":room_id:"+busKey);
	}
}
