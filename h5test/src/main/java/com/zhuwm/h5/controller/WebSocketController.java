package com.zhuwm.h5.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import com.zhuwm.util.CookieUtil;

/*
 * ======
 * 要在类路径上加tomcat的两个jar包：catalina.jar，和tomcat_coyote.jar
 */
@Controller
public class WebSocketController  extends DispatcherServlet {

	private static final long serialVersionUID = 1L;
	
	@RequestMapping(value = "/websocketlogin.do")
	public ModelAndView websocketLogin(HttpServletRequest request, HttpServletResponse response) {

		
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
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("websocket/index");
		
		//如果cookie中有保存userId，则已登录
		if(userId!=null){
			mav.addObject("userId",userId);
			return mav;
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

		mav.addObject("userId",loginUserId);
		
		return mav;
	}
	
	
	
	
	@RequestMapping(value = "/websocket.do")
	public ModelAndView websocketIndex() {

		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("websocket/index");
		return mav;
	}

	
}
