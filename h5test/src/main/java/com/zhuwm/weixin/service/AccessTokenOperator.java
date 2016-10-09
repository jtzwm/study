package com.zhuwm.weixin.service;

import java.io.IOException;
import java.util.Date;

import org.json.JSONObject;

import sun.util.logging.resources.logging;

public class AccessTokenOperator {

	private static String access_token;

	private static long GetAccessToken_Time = 0;

	/**
	 * 获取token
	 * @author littl
	 * @return
	 */
	public static String getAccessToken(String appid, String secret) {
		
		if (access_token == null || isExpired()==true) {

			access_token = getAccessTokenFromHttp(appid, secret);
			System.out.println("=======过期后重新取token："+access_token);
			GetAccessToken_Time = System.currentTimeMillis();
		} 
		System.out.println("=======直接返回token："+access_token);
		return access_token;
	}
	
	public static boolean isExpired(){
		return (System.currentTimeMillis() - GetAccessToken_Time) / 1000 > 7200;
	}

	/**
	 * 获取token
	 * @author littl
	 * @param appid
	 * @param secret
	 * @return
	 */
	private static String getAccessTokenFromHttp(String appid, String secret) {
		String access_token = null;
		StringBuffer action = new StringBuffer();
		action.append("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential").append("&appid=" + appid).append("&secret=" + secret);

		try {
			String resp = HttpUtil.httpGet(action.toString());
			JSONObject jsonObject = new JSONObject(resp);
			// JSONObject.fromObject(resp);
			System.out.println("access_token:" + jsonObject.toString());
			Object object = jsonObject.get("access_token");
			if (object != null) {
				access_token = String.valueOf(object);
			}
			return access_token;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
