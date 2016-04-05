package com.zhuwm.h5.websocket;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

@ServerEndpoint(value = "/websocket/{user}")  
public class WebScoketServer  {


	private static Map<String,String> sessionUserMap=new HashMap<String,String>();
	
    private Session session;  
    private static final Logger Log = Logger.getLogger("WebSocketServer");  
      
    @OnOpen  
    public void open(Session session,  @PathParam(value = "user")String userId) {  
        this.session = session;  
          
        Log.info("*** WebSocket opened from sessionId " + session.getId()+",user:"+userId);
        sessionUserMap.put(session.getId(), userId);
        WebScoketServerAdvisor.putSession(session,userId);
        
    }  
      
    @OnMessage  
    public void inMessage(String message) {  
        Log.info("*** WebSocket Received from sessionId " + this.session.getId() + ": " + message);
        
        WebScoketServerAdvisor.receivedMessageFromSession(session,message);
    }  
      
    @OnClose  
    public void end() {  
        Log.info("*** WebSocket closed from sessionId " + this.session.getId());  
        WebScoketServerAdvisor.RemovesSession(sessionUserMap.get(this.session.getId()));
    }

	
}
