package com.zhuwm.redis;

import com.zhuwm.h5.websocket.WebScoketServerAdvisor;

public class WebOnLineUserObserver implements IOnLineUserObserver {

	public void onLineNotice() {
		// TODO Auto-generated method stub
		System.out.println("WebOnLineUserObserver worked.");
		WebScoketServerAdvisor.sendMessageToAll("");
	}

}
