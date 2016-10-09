package com.zhuwm.weixin.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 处理http请求，以后用apache工具类替换
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author littl<br>
 * 开发时间: 2016年10月8日<br>
 */
public class HttpUtil {

	public static String httpGet(String strUrl) throws IOException {
		URL url = new URL(strUrl);
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("GET");
		http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		http.setDoInput(true);
		InputStream is = http.getInputStream();
		int size = is.available();
		byte[] buf = new byte[size];
		is.read(buf);
		String resp = new String(buf, "UTF-8");
		return resp;
	}

	public static String httpPost(String strUrl, String sendMsg) throws IOException {
		URL url = new URL(strUrl);
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("POST");
		http.setDoOutput(true);
		http.setDoInput(true);
		http.connect();
		OutputStream os = http.getOutputStream();
		os.write(sendMsg.getBytes("UTF-8"));// 传入参数
		os.flush();
		os.close();

		InputStream is = http.getInputStream();
		int size = is.available();
		byte[] jsonBytes = new byte[size];
		is.read(jsonBytes);
		String message = new String(jsonBytes, "UTF-8");

		return message;
	}

}
