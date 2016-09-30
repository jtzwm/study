package com.zhuwm.weixin.service;

import java.util.Date;

import com.zhuwm.weixin.po.ReceiveXmlEntity;

/**
 * 处理各种返回消息，生成微信的xml文件
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author littl<br>
 * 开发时间: 2016年9月30日<br>
 */
public class ResopnseMsgGenerator {
	
	/**
	 * 生成文本消息的xml
	 * @author littl
	 * @param resopenseResult 
	 * @param xmlEntity 
	 * @return
	 */
	public static String generateTextMsg(ReceiveXmlEntity xmlEntity, String resopenseResult){
		
        StringBuffer sb = new StringBuffer();  
        Date date = new Date();  
        sb.append("<xml><ToUserName><![CDATA[");  
        sb.append(xmlEntity.getFromUserName());  
        sb.append("]]></ToUserName><FromUserName><![CDATA[");  
        sb.append( xmlEntity.getToUserName());  
        sb.append("]]></FromUserName><CreateTime>");  
        sb.append(date.getTime());  
        sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");  
        sb.append(resopenseResult);  
        sb.append("]]></Content></xml>");  
        return sb.toString();  
		
	}
	
	/**
	 * 生成图文消息
	 * @author littl
	 * @param xmlEntity
	 * @return
	 */
	public static String generateNewsMsg(ReceiveXmlEntity xmlEntity){
        StringBuffer sb = new StringBuffer();  
        Date date = new Date();  
        sb.append("<xml><ToUserName><![CDATA[");  
        sb.append(xmlEntity.getFromUserName());  
        sb.append("]]></ToUserName><FromUserName><![CDATA[");  
        sb.append( xmlEntity.getToUserName());  
        sb.append("]]></FromUserName><CreateTime>");  
        sb.append(date.getTime());  
        sb.append("</CreateTime><MsgType><![CDATA[news]]></MsgType>");  
        sb.append("<ArticleCount>1</ArticleCount>");
        sb.append("<Articles>");
        sb.append("<item>");
        sb.append("<Title><![CDATA[图文消息测试]]></Title>"); 
        sb.append("<Description><![CDATA[欢迎注册并登录]]></Description>");
        sb.append("<PicUrl><![CDATA[http://e.hiphotos.baidu.com/exp/w=480/sign=d90f895aad51f3dec3b2b86ca4eff0ec/241f95cad1c8a7868d0e0b9e6509c93d70cf507d.jpg]]></PicUrl>");
        sb.append("<Url><![CDATA[http://15z8j42945.iask.in/f7index.do?openid="+xmlEntity.getFromUserName()+"]]></Url>");
        sb.append("</item>");
        sb.append("</Articles>");
        sb.append("</xml>");  
        return sb.toString();  
		
	}
}
