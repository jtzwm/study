package com.zhuwm.weixin.service;

import org.springframework.stereotype.Service;

@Service
public class CreateMenuService {

	public void createMenu() {
		// 获取token
		String access_token=AccessTokenOperator.getAccessToken(Constant.appid, Constant.secret);
		
		//创建菜单
		
	}

}
