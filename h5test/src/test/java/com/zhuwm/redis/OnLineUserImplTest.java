package com.zhuwm.redis;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.zhuwm.redis.jedis.JedisUtil;

import redis.clients.jedis.Jedis;


public class OnLineUserImplTest {

    private Jedis jedis; 
    @Before
    public void setup() {
    	jedis=JedisUtil.getJedis();
    	
    	Long i=jedis.llen(OnLineUserImpl.B_S_ONLINEUSERQUENE_NAME);
    	for (int j = 0; j < i; j++) {
    		jedis.lpop(OnLineUserImpl.B_S_ONLINEUSERQUENE_NAME);
		}
		
		OnLineUserImpl onLineUserImpl = new OnLineUserImpl();
		onLineUserImpl.putUserToQueue("0001");
		onLineUserImpl.putUserToQueue("0001");
		onLineUserImpl.putUserToQueue("0002");
		onLineUserImpl.putUserToQueue("0002");
		System.out.println("setup:"+jedis.llen(OnLineUserImpl.B_S_ONLINEUSERQUENE_NAME));
    	
    }

	@After
	public void tearDown() throws Exception {
		System.out.println("tearDown:"+jedis.llen(OnLineUserImpl.B_S_ONLINEUSERQUENE_NAME));
		JedisUtil.returnResource(jedis);		
	}

	@Test
	public void testPutUserToQueue() {
		
		OnLineUserImpl onLineUserImpl = new OnLineUserImpl();
		onLineUserImpl.putUserToQueue("1001");
		
		Long temp=jedis.lrem(OnLineUserImpl.B_S_ONLINEUSERQUENE_NAME, 0, "1001");
		
		assertEquals(1, temp.longValue());
		
		System.out.println("testPutUserToQueue:"+jedis.llen(OnLineUserImpl.B_S_ONLINEUSERQUENE_NAME));
	}

	@Test
	public void testGetQueueCount() {
		
		
	
		OnLineUserImpl onLineUserImpl = new OnLineUserImpl();
		onLineUserImpl.putUserToQueue("2001");
		onLineUserImpl.putUserToQueue("2001");
		onLineUserImpl.putUserToQueue("2002");
		
		Long temp=onLineUserImpl.getQueueCount();
		assertEquals(4, temp.longValue());
		
		System.out.println("testGetQueueCount:"+jedis.llen(OnLineUserImpl.B_S_ONLINEUSERQUENE_NAME));
	}

	@Test
	public void testGetLastUserFromQueue() {

		OnLineUserImpl onLineUserImpl = new OnLineUserImpl();
		onLineUserImpl.putUserToQueue("3001");
		onLineUserImpl.putUserToQueue("3002");
		onLineUserImpl.putUserToQueue("0001");
		String temp=onLineUserImpl.getLastUserFromQueue();		
		assertEquals("0002", temp);
		System.out.println("testGetLastUserFromQueue:"+jedis.llen(OnLineUserImpl.B_S_ONLINEUSERQUENE_NAME));
	}

}
