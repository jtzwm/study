package com.zhuwm.weixin.service;

import org.springframework.stereotype.Service;

import com.zhuwm.weixin.po.ReceiveXmlEntity;

@Service
public class WeiXinService {

	public String processWechatMsg(String xml) {
        /** 解析xml数据 */  
        ReceiveXmlEntity xmlEntity = new ReceiveXmlProcess().getMsgEntity(xml);  
          
        /** 以文本消息为例，调用图灵机器人api接口，获取回复内容 */  
        String receiveResult = "";  
        String resopenseResult="";
        //下面就是处理各种消息类型
        if("text".endsWith(xmlEntity.getMsgType())){  
 
        	System.out.println("*****收到的消息是:"+xmlEntity.getContent());
        	receiveResult=xmlEntity.getContent();
        	String temp="<a href=\"http://15z8j42945.iask.in/f7index.do\">测试链接</a>";
        	resopenseResult = "收到您的消息:"+receiveResult+temp;  
        }  
          

        /** 此时，如果用户输入的是“你好”，在经过上面的过程之后，result为“你也好”类似的内容  
         *  因为最终回复给微信的也是xml格式的数据，所有需要将其封装为文本类型返回消息 
         * */  
        resopenseResult = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), resopenseResult);  
          
        return resopenseResult;  
	}

}
