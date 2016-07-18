package com.zhuwm.h5.websocket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.Session;
/**
 * 对WebSocketServer的封装，需要操作WebSocket信息，只需要从此类获取。
 * 包括获取指定ID的session、向所有session发消息。
 * @author zhuweiming
 *
 */
public class WebScoketServerAdvisor {
	
	//TODO要改成用Map实现
	private static CopyOnWriteArraySet <Session> webScoketSessions =new CopyOnWriteArraySet<Session>();
	
	private static int onLineUsersCount =0;
	
	public static int getSessionCount(){
		return webScoketSessions.size();
	}
	
	public static void putSession(String userId,Session session){
		//TODO 要改成使用Map
		webScoketSessions.add(session);
		onLineUsersCount++;
	}
	
	public static void RemovesSession(String userId,Session session){
		
		//TODO掉线时要从Map中删除掉
		webScoketSessions.remove(session);
		onLineUsersCount--;
		
	}
	

	
	public static void sendMessageToAll(String message){
		for (Session session : webScoketSessions) {
			try {
				session.getBasicRemote().sendText("当前等待用户数:"+webScoketSessions.size());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void receivedMessageFromSession(Session session, String message) {
		sendMessageToAll("来自 "+session.getId()+"的消息："+message);
		
	}

}
