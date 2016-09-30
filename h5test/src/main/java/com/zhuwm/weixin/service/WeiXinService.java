package com.zhuwm.weixin.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.zhuwm.weixin.po.ReceiveXmlEntity;

@Service
public class WeiXinService {
	
	private static ArrayList<String> openIDList = new ArrayList<>();
	static{
		openIDList.add("oS9eBuN7JF2EJLGVtG8RCNp20HAQ");//自己的openID
		//openIDList.add("");
	}

	public String processWechatMsg(String xml) {
        /** 解析xml数据 */  
        ReceiveXmlEntity xmlEntity = new ReceiveXmlProcess().getMsgEntity(xml);  
          
        String receiveResult = "";  
        String resopenseResult="";
        //下面就是处理各种消息类型
        if("text".endsWith(xmlEntity.getMsgType())){  
        	
        	System.out.println("*****收到文本消息:"+xmlEntity.getContent());
        	receiveResult=xmlEntity.getContent();
        	
        	//文本消息
        	//String temp="<a href=\"http://15z8j42945.iask.in/f7index.do\">测试链接</a>";
        	//resopenseResult = "收到您的消息:"+receiveResult+temp;  
        	//resopenseResult = ResopnseMsgGenerator.generateTextMsg(xmlEntity,resopenseResult);
        	//图文消息
        	resopenseResult = ResopnseMsgGenerator.generateNewsMsg(xmlEntity);
        } else if("event".endsWith(xmlEntity.getMsgType())){
        	
        	if("subscribe".equals(xmlEntity.getEvent())){
        		//订阅事件
        		System.out.println("+++收到关注事件:"+xmlEntity.getFromUserName());
        		resopenseResult = ResopnseMsgGenerator.generateNewsMsg(xmlEntity);
        	}else{
        		//取消订阅事件
        		System.out.println("+++收到取消关注事件:"+xmlEntity.getFromUserName());
        	}
        }
          


        return resopenseResult;  
	}

}
