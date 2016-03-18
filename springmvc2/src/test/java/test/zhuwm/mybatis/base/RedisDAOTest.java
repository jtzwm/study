package test.zhuwm.mybatis.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.zhuwm.mybatis.base.RedisDAO;


public class RedisDAOTest {
	RedisDAO redisDAO;

	@Before
	public void setUp() throws Exception {
		redisDAO=new RedisDAO();
		
	}

	@After
	public void tearDown() throws Exception {
		redisDAO.returnResource();
		
	}

	@Test
	public void testGetObjectFromRedis(){
		String result=(String)redisDAO.getObjectFromRedis("name");
		assertEquals(result,"zhuwm");
	}
	


	@Test
	public void testCreateKeyStringString() {
		String result=redisDAO.createKey("name", "zhuwm");
		System.out.println("testCreateKeyStringString:"+result);
		assertEquals(result, "OK");
	}
	
	@Test
	public void testSetKeyAndExpiredTime(){
		String result=redisDAO.createKeyAndExpiredTime("world",1,"fuck");
		System.out.println("createKeyAndExpiredTime:"+result);
		assertEquals(result, "OK");
	}
	
	@Test
	public void testSetIDAndIncrString(){
		String result1=redisDAO.getIDAndIncr("ID:OrderID");
		String result2=redisDAO.getIDAndIncr("ID:CustomerID");
		String result3=redisDAO.getIDAndIncr("ID:ProjectID");
		//System.out.println("testSetIDAndIncrString:"+result1);
		assertEquals(result1, "4");
		assertEquals(result2, "2");
		assertEquals(result3, "1");
	}

}
