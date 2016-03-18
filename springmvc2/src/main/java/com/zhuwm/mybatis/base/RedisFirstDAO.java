package com.zhuwm.mybatis.base;

public abstract class RedisFirstDAO extends RedisDAO {

	/**
	 * DAO基础方法。
	 * 优先从Redis获取单个对象，如果取不到，再从数据库获取。
	 * @author zhuweiming
	 */
	public Object getSinglerObjectRedisFirst(String key) {
		if (key == null) return null;
		Object result = jedis.get(key);
		
		if (result ==null)
			result=getObjectFromDataBase();
		
		if (result !=null)
			//TODO 这里要处理下，把对象转成json对象
			jedis.set(key, result.toString());
		return result;
	}
	
	/**
	 * DAO基础方法
	 * 优先从Redis获取列表，如果取不到，再从数据库获取。
	 * @author zhuweiming
	 * @param key
	 * @return
	 */
	public Object getListObjectsRedisFirst(String key){
		return null;
	}

	/**
	 * 从数据库获取的方法，需要子类自行实现
	 * @author zhuweiming
	 * @return
	 */
	protected abstract Object getObjectFromDataBase();


}
