package com.zhuwm.weixin.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import com.zhuwm.weixin.service.CreateMenuService;
import com.zhuwm.weixin.service.MsgService;
import com.zhuwm.weixin.service.UserService;
import com.zhuwm.weixin.service.WeiXinService;

@Controller
public class WeiXinController extends DispatcherServlet {

	@Autowired
	private WeiXinService weixinService;
	@Autowired
	MsgService msgService;

	@Autowired
	CreateMenuService createMenuService;
	@Autowired
	UserService userService;

	/**
	 * 处理微信接收到的所有消息及事件的方法
	 * @author littl
	 * @param request
	 * @param response
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	// 加produces一段，是因为spring 缺省的handle，会对返回参数进行处理
	@RequestMapping(value = "/weixin.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String weixinMsg(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String token = "zhuwm";
		// 获取微信平台加的参数
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");

		// 读取接收到的xml消息
		String xml = getSendXml(request);

		String result = "";
		/** 判断是否是微信接入激活验证，只有首次接入验证时才会收到echostr参数，此时需要把它直接返回 */
		if (echostr != null && echostr.length() > 1) {
			result = echostr;
		} else {
			// 正常的微信处理流程
			result = weixinService.processWechatMsg(xml);
			System.out.println("*******返回消息为：" + result);
		}
		return result;

		// 下面这段代码，是验证token的，所有请求，都应该先验证。可以避免非法请求
		/*		String tmpStr = getSHA1(token, timestamp, nonce);
				if (tmpStr.equals(signature)) {
					return echostr;
				} else {
					return null;
				}*/

	}

	/**
	 * 微信测试的页面
	 * @author littl
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/weixinIndex.do")
	public ModelAndView weixinIndex(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("weixin/index");
		return mav;

	}

	/**
	 * 处理群发消息的ajax方法
	 * @author littl
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/ajaxSendMsg.do")
	public ArrayList ajaxSendMsg(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("===收到ajax请求：" + request.getParameter("msg"));
		String type = request.getParameter("type");
		msgService.setType(type);
		msgService.sendMsgToAll();
		ArrayList<String> resultList = new ArrayList<String>();
		resultList.add("success");
		return resultList;

	}

	/**
	 * 创建自定义菜单的ajax方法
	 * @author littl
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/createMenu.do")
	public Map<String, String> createMenu(HttpServletRequest request, HttpServletResponse response) {

		String result = createMenuService.createMenu();

		ArrayList<String> resultList = new ArrayList<String>();
		resultList.add("success");
		resultList.add(result);

		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("resultcode", result);
		resultMap.put("errormsg", "仅仅是测试");

		return resultMap;
	}

	/**
	 * 获取用户列表的ajax方法
	 * @author littl
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserList.do")
	public ArrayList getUserList(HttpServletRequest request, HttpServletResponse response) {
		//TODO接口一次只能返回10000条用户opendid，以后要处理超过1万用户的情况。
		String strResponse=userService.getUserList();
		ArrayList<String> resultList = new ArrayList<String>();
		resultList.add(strResponse);
		return resultList;

	}
	
	
	@ResponseBody
	@RequestMapping(value = "/sendMsgToUser.do")
	public ArrayList SendMsgToUser(HttpServletRequest request, HttpServletResponse response) {
		String openid = request.getParameter("openid");
		System.out.println("===向单个用户发送消息：" + openid);
		msgService.sendMsgToUser(openid);
		ArrayList<String> resultList = new ArrayList<String>();
		resultList.add("success");
		return resultList;

	}

	/**
	 * 从requset中获取xml
	 * @author littl
	 * @param request
	 * @throws IOException
	 */
	private String getSendXml(ServletRequest request) throws IOException {
		/** 读取接收到的xml消息 */
		StringBuffer sb = new StringBuffer();
		InputStream is = request.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String s = "";
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}
		String xml = sb.toString(); // 次即为接收到微信端发送过来的xml数据
		System.out.println("===收到微信端发送的xml为：" + xml);
		return xml;
	}

	public String getSHA1(String token, String timestamp, String nonce) throws NoSuchAlgorithmException {
		String[] array = new String[] {token, timestamp, nonce};
		StringBuffer sb = new StringBuffer();
		// 字符串排序
		Arrays.sort(array);
		for (int i = 0; i < 3; i++) {
			sb.append(array[i]);
		}
		String str = sb.toString();
		// SHA1签名生成
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(str.getBytes());
		byte[] digest = md.digest();

		StringBuffer hexstr = new StringBuffer();
		String shaHex = "";
		for (int i = 0; i < digest.length; i++) {
			shaHex = Integer.toHexString(digest[i] & 0xFF);
			if (shaHex.length() < 2) {
				hexstr.append(0);
			}
			hexstr.append(shaHex);
		}
		return hexstr.toString();
	}
}
