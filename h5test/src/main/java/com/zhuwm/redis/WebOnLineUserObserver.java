package com.zhuwm.redis;

import java.util.List;

import com.zhuwm.h5.websocket.WebScoketServerAdvisor;

public class WebOnLineUserObserver implements IOnLineUserObserver {

	public void onLineNotice() {
		// TODO Auto-generated method stub
		System.out.println("WebOnLineUserObserver worked.");
		WebScoketServerAdvisor.sendMessageToAll("有用户登录");
		
		OnLineUserImpl onLineUserImpl = new OnLineUserImpl();
		List <String> listUserId =onLineUserImpl.getQueueUserList();
		for(int i=0; i<listUserId.size();i++){
			String userId=listUserId.get(i);
			
			//通知所有h5用户，自己在列表中的位置
			WebScoketServerAdvisor.sendMessage(userId, i+"");
		}
		
		onLineUserImpl.releaseJedis();
	}

}
