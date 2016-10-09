package com.zhuwm.weixin.service;

import java.io.IOException;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class CreateMenuService {

	public String createMenu() {
		// 获取token
		String access_token = AccessTokenOperator.getAccessToken(Constant.getAppid(), Constant.getSecret());

		// 创建菜单
		String result = createMenu(access_token);
		return result;

	}

	private String createMenu(String access_token) {
		StringBuffer action = new StringBuffer();
		action.append("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=").append(access_token);

		String menus = getMenus();
		try {
			String jsonResponse = HttpUtil.httpPost(action.toString(), menus);
			JSONObject object = new JSONObject(jsonResponse);
			return object.getString("errmsg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "-1";

	}

	private String getMenus() {
		StringBuffer str = new StringBuffer("");

		str.append("{");
		str.append("\"button\":[");
		str.append("{");
		str.append("\"name\":\"自选\",");
		str.append("\"sub_button\":[");
		str.append("{");
		str.append("\"type\":\"click\",");
		str.append("\"name\":\"我的自选\",");
		str.append("\"key\":\"mystock\"");
		str.append("},");
		str.append("{");
		str.append("\"type\":\"click\",");
		str.append("\"name\":\"添加自选\",");
		str.append("\"key\":\"addstock\"");
		str.append("},");
		str.append("{");
		str.append("\"type\":\"click\",");
		str.append("\"name\":\"股票推荐\",");
		str.append("\"key\":\"recommend\"");
		str.append("}");
		str.append("]");
		str.append("},");
		str.append("{");
		str.append("\"name\":\"交易\",");
		str.append("\"sub_button\":[");
		str.append("{");
		str.append("\"type\":\"click\",");
		str.append("\"name\":\"买入\",");
		str.append("\"key\":\"buy\"");
		str.append("},");
		str.append("{");
		str.append("\"type\":\"click\",");
		str.append("\"name\":\"卖出\",");
		str.append("\"key\":\"sell\"");
		str.append("},");
		str.append("{");
		str.append("\"type\":\"view\",");
		str.append("\"name\":\"交易平台\",");
		str.append("\"url\":\"http://15z8j42945.iask.in/f7index.do\"");
		str.append("}");
		str.append("]");
		str.append("},");
		str.append("{");
		str.append("\"type\":\"click\",");
		str.append("\"name\":\"联系我们\",");
		str.append("\"key\":\"contact\"");
		str.append("}");
		str.append("]");

		return str.toString();
	}

}
