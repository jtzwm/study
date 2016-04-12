package com.zhuwm.util;

import javax.servlet.http.Cookie;

/**
 * 操作cookie的工具类
 * @author zhuweiming
 *
 */
public class CookieUtil {
	
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
