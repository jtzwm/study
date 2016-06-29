package com.zhuwm.h5.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

import com.zhuwm.redis.OnLineUserImpl;

@ServerEndpoint(value = "/websocket/{user}")  
public class WebScoketServer  {


		
    private Session session;  
    private static final Logger Log = Logger.getLogger("WebSocketServer");  
      
    @OnOpen  
    public void open(Session session,  @PathParam(value = "user")String userId) {  
        this.session = session;  
          
        Log.info("*** WebSocket opened from sessionId " + session.getId()+",user:"+userId);
        

        OnLineUserImpl onlineUser= new OnLineUserImpl();

        //将用户放入队列中（这种方法适用于视频队列等需要排队的）
        onlineUser.putUserToQueue(userId);
        //保存sessionId所对应的userId，为了在后续事件中方便取到。如果是真正的项目，userId都在cookie或session中了。
        onlineUser.putSessionId(session.getId(), userId);
        onlineUser.releaseJedis();
        
        //TODO session可以保存在redis中。
        WebScoketServerAdvisor.putSession(userId,session);
        
    }  
      
    @OnMessage  
    public void inMessage(String message) {  
        Log.info("*** WebSocket Received from sessionId " + this.session.getId() + ": " + message);
        
        WebScoketServerAdvisor.receivedMessageFromSession(session,message);
    }  
      
    @OnClose  
    public void end() {  
        Log.info("*** WebSocket closed from sessionId " + this.session.getId());  
        
        OnLineUserImpl onlineUser= new OnLineUserImpl();
        String userId=onlineUser.getUserId(this.session.getId());
        onlineUser.releaseJedis();
        
        WebScoketServerAdvisor.RemovesSession(userId);
    }

	
}
