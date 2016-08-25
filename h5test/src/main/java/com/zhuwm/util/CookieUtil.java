package com.zhuwm.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 操作cookie的工具类
 * @author zhuweiming
 *
 */
public class CookieUtil {
	
	//从requset中获取cookie
	public static String getCookiesFromRequest(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		//String userId=null;
		if(cookies !=null){
			for(Cookie c :cookies){
				if(c.getName().equals("userId")){
					return c.getValue();
				}
			}
		}
		return null;
	}
	
	/**
	 * 获取Cookie
	 * @param id
	 * @param value
	 * @return
	 */
	public static Cookie getNomalCookis(String id,String value){
		Cookie cookie =new Cookie(id,value);
		return cookie;
	}
	
	public static void madeMD5(Cookie cookie){
		
	}

}
