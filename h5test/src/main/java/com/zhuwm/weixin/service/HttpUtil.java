package com.zhuwm.weixin.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	
	public static String httpGet(String strUrl) throws IOException{
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

}
