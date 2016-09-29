package com.zhuwm.weixin.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class GroupMsgService {
	
	private String access_token="";
	private String type;

	public void sendMsgToAll() {
		testsendTextByOpenids();
	}
	
	public void testsendTextByOpenids(){
        String reqjson =createGroupText(getOpenids());
		
        String urlstr ="https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";
        urlstr=urlstr.replace("ACCESS_TOKEN", access_token);

        System.out.println("===生成群发json："+reqjson);
        try {
             
            URL httpclient =new URL(urlstr);
            HttpURLConnection conn =(HttpURLConnection) httpclient.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(2000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
            conn.setDoOutput(true);        
            conn.setDoInput(true);
            conn.connect();
            OutputStream os= conn.getOutputStream();    
            System.out.println("req:"+reqjson);
            os.write(reqjson.getBytes("UTF-8"));//传入参数    
            os.flush();
            os.close();
            
            InputStream is =conn.getInputStream();
            int size =is.available();
            byte[] jsonBytes =new byte[size];
            is.read(jsonBytes);
            String message=new String(jsonBytes,"UTF-8");
            System.out.println("resp:"+message);
         
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
	
    private String createGroupText(JSONArray array){
        JSONObject gjson =new JSONObject();
        gjson.put("touser", array);
        gjson.put("msgtype", "text");
        JSONObject text =new JSONObject();
        text.put("content", "群发测试.");
        gjson.put("text", text);
       return gjson.toString();
   }

	
    public  JSONArray  getOpenids(){
        JSONArray array =null;
        String urlstr ="https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
        urlstr =urlstr.replace("ACCESS_TOKEN", getAccess_token());
        urlstr =urlstr.replace("NEXT_OPENID", "");
        try {

            String resp =conn(urlstr);
            JSONObject jsonObject =new JSONObject(resp);
            System.out.println("resp:"+jsonObject.toString());
            array =jsonObject.getJSONObject("data").getJSONArray("openid");
            System.out.println("===取到openid:"+array.toString());
            return array;
        } catch (MalformedURLException e) {
            e.printStackTrace();
             return array;
             
        } catch (IOException e) {
            e.printStackTrace();
             return array;
         
        }
    }

	public String getAccess_token() {

		String appid;
		String secert;
		if("1".equals(this.type)){
			//测试号
			 appid="wx7809d8c9c6bcb871";
			 secert="8b020e7264c7978edb058eaf03aead9d";
		}else{
			//信城通帐号
			appid="wx808fe99c9fc7798e";
			secert="bed48d4155db75aef17d4ee3cd99fda3";
			
		}


		StringBuffer action = new StringBuffer();
		action.append("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential")
			.append("&appid="+appid)
			.append("&secret="+secert);
		
		try {
			String resp = conn(action.toString());
			JSONObject jsonObject = new JSONObject(resp);
			// JSONObject.fromObject(resp);
			System.out.println("access_token:" + jsonObject.toString());
			Object object = jsonObject.get("access_token");
			if (object != null) {
				access_token = String.valueOf(object);
			}
			return access_token;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return access_token;
		} catch (IOException e) {
			e.printStackTrace();
			return access_token;
		}
	}

	
	private String conn(String strUrl) throws IOException{
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

	public void setType(String type) {
		this.type=type;
		
	}
    
}
