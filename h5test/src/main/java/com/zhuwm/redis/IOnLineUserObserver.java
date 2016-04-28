package com.zhuwm.redis;

/**
 * 在线用户的观察者接口，各种需要观察在线用户状态变化的类，都可以实现此接口。
 * @author zhuweiming
 *
 */
public interface IOnLineUserObserver {
	
	public void onLineNotice();
	
	

}
