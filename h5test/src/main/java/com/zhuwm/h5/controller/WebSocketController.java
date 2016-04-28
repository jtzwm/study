package com.zhuwm.h5.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;


/*
 * ======
 * 要在类路径上加tomcat的两个jar包：catalina.jar，和tomcat_coyote.jar
 */
@Controller
public class WebSocketController  extends DispatcherServlet {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 处理登录的ajax方法，返回json数据
	 * @author zhuweiming
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody  
	@RequestMapping(value = "/websocketlogin.do")
	public List<String> websocketLogin(HttpServletRequest request, HttpServletResponse response) {

		
		//首先取cookid中的userId
		Cookie[] cookies = request.getCookies();
		String userId=null;
		if(cookies !=null){
			for(Cookie c :cookies){
				if(c.getName().equals("userId")){
					userId=c.getValue();
				}
			}
		}
		
		ArrayList<String> resultList= new ArrayList<String>();
		
		//如果cookie中有保存userId，则已登录
		if(userId!=null){
			resultList.add(userId);
			return resultList;
		}
		
		//没有userId，则未登录。从request中取值来判断
		String loginUserId=request.getParameter("userId");
		if(loginUserId!=null && ! loginUserId.equals("")){
			System.out.println("userId:"+loginUserId);
			//创建新的cookie并保存。
			Cookie cookie=new Cookie("userId", loginUserId);
			response.addCookie(cookie);	
		}else{
			loginUserId="";
		}

		resultList.add(loginUserId);
		return resultList;
	}
	
	
	
	
	@RequestMapping(value = "/websocketindex.do")
	public ModelAndView websocketIndex() {
				
		ModelAndView mav = new ModelAndView();
		mav.setViewName("websocket/index");
		return mav;
	}

	
}
