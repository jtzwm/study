package com.zhuwm.h5.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
/**
 * 对WebSocketServer的封装，需要操作WebSocket信息，只需要从此类获取。
 * 包括获取指定ID的session、向所有session发消息。
 * @author zhuweiming
 *
 */
public class WebScoketServerAdvisor {
	
	//TODO要改成用Map实现
	private static Map<String,Session> webScoketSessions =new HashMap <String,Session> ();
	
	private static int onLineUsersCount =0;
	
	public static int getSessionCount(){
		return webScoketSessions.size();
	}
	
	public static void putSession(String userId,Session session){
		webScoketSessions.put(userId, session);
		onLineUsersCount++;
	}
	
	public static void RemovesSession(String userId,Session session){
		
		//TODO掉线时要从Map中删除掉
		webScoketSessions.remove(userId);
		onLineUsersCount--;
		
	}
	

	/**
	 * 向所有websocket用户发送同一消息
	 * @author littl
	 * @param message
	 */
	public static void sendMessageToAll(String message){
		for (Map.Entry entry : webScoketSessions.entrySet()){
			try {
				 String key = entry.getKey().toString();
				 Session session = (Session)entry.getValue();
				session.getBasicRemote().sendText("当前等待用户数:"+webScoketSessions.size());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 向单个用户发送消息
	 * @author littl
	 * @param message
	 */
	public static void sendMessage(String userId,String countNo){
		
		//通过userId取session
		
		//通过session发送
			try {
				Session session = webScoketSessions.get(userId);
				if(session!=null)
					session.getBasicRemote().sendText("当前等待用户数:"+webScoketSessions.size());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	
	public static void receivedMessageFromSession(Session session, String message) {
		sendMessageToAll("来自 "+session.getId()+"的消息："+message);
		
	}

}
