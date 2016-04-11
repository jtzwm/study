package com.zhuwm.redis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.zhuwm.redis.jedis.JedisUtil;

import redis.clients.jedis.Jedis;


public class VideoQueueImplTest {
    private Jedis jedis; 
	
	@Before
	public void setUp() throws Exception {
		
    	jedis=JedisUtil.getJedis();
    	Long i=jedis.llen(VideoQueueImpl.B_S_VIDEOQUENE_NAME);
    	for (int j = 0; j < i; j++) {
    		jedis.lpop(VideoQueueImpl.B_S_VIDEOQUENE_NAME);
		}
	}

	@After
	public void tearDown() throws Exception {
		JedisUtil.returnResource(jedis);		
	}

	@Test
	public void testAddUserToVideoQueue() {
		VideoQueueImpl impl=new VideoQueueImpl();
		String busKey="0001";
		impl.addUserToVideoQueue(busKey, "0001");
		Long temp=jedis.llen(VideoQueueImpl.B_S_VIDEOQUENE_NAME);
		Boolean temp1=jedis.exists(VideoQueueImpl.B_S_VIDEOQUENE_NAME+":room_id:"+busKey);
		assertEquals(temp.longValue(), 1);
		
		assertTrue(temp1);
	}

	@Test
	public void testPopFromVideoQueue() {
		VideoQueueImpl impl=new VideoQueueImpl();
		
		
		impl.addUserToVideoQueue("0001", "0001");
		impl.addUserToVideoQueue("0002", "0002");
		Long temp=jedis.llen(VideoQueueImpl.B_S_VIDEOQUENE_NAME);
		
		String busKey=impl.popFromVideoQueue();
		assertEquals(busKey, "0001");
		
		Boolean temp1=jedis.exists(VideoQueueImpl.B_S_VIDEOQUENE_NAME+":room_id:"+busKey);		
		assertFalse(temp1);
	}

	@Test
	public void testGetRoomIdByKey() {
		fail("Not yet implemented");
	}

}
