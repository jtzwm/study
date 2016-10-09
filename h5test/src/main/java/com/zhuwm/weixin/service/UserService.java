package com.zhuwm.weixin.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	public String getUserList(){
		String token=AccessTokenOperator.getAccessToken(Constant.getAppid(), Constant.getSecret());
		String url="https://api.weixin.qq.com/cgi-bin/user/get?access_token="+token;
		String response="";
		try {
			 response=HttpUtil.httpGet(url);
			System.out.println("----------获取用户列表："+response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

}
