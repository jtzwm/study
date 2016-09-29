package com.zhuwm.weixin.service;
import java.util.Date; 

/**
 * 功能说明: 封装返回的xml格式结果<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author littl<br>
 * 开发时间: 2016年9月29日<br>
 */
public class FormatXmlProcess {
    public String formatXmlAnswer(String to, String from, String content) {  
        StringBuffer sb = new StringBuffer();  
        Date date = new Date();  
        sb.append("<xml><ToUserName><![CDATA[");  
        sb.append(to);  
        sb.append("]]></ToUserName><FromUserName><![CDATA[");  
        sb.append(from);  
        sb.append("]]></FromUserName><CreateTime>");  
        sb.append(date.getTime());  
        sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");  
        sb.append(content);  
        sb.append("]]></Content><FuncFlag>0</FuncFlag></xml>");  
        return sb.toString();  
    } 
}
