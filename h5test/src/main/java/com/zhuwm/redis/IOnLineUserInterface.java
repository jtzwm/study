package com.zhuwm.redis;

/**
 *  在线用户的接口类，用于跟observer类配合
 * @author zhuweiming
 *
 */
public interface IOnLineUserInterface {
	
	public void registerObserver(IOnLineUserObserver observer);

}
