package com.zhuwm.weixin.service;

import java.io.IOException;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

public class ContentOpertor {

	public static String getContentsList() throws IOException  {
		// 获取token
		String access_token = AccessTokenOperator.getAccessToken(Constant.getAppid(), Constant.getSecret());
		// 获取contents列表
		String strUrl = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=" + access_token;
		String strResponds=HttpUtil.httpPost(strUrl, getSendMsg());
		return strResponds;
	}

	private static String getSendMsg() {
		StringBuffer str = new StringBuffer();

		str.append("{");
		str.append("\"type\":news,");
		str.append("\"offset\":0,");
		str.append("\"count\":2");
		str.append("}");
		return str.toString();
	}

}
