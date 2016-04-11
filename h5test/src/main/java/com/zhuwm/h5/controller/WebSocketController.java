package com.zhuwm.h5.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import com.zhuwm.h5.service.IndexService;

/*
 * ======
 * 要在类路径上加tomcat的两个jar包：catalina.jar，和tomcat_coyote.jar
 */
@Controller
public class WebSocketController  extends DispatcherServlet {

	private static final long serialVersionUID = 1L;
	
	@RequestMapping(value = "/websocketlogin.do")
	public ModelAndView websocketLogin(HttpServletRequest request, HttpServletResponse response) {

		String userId=request.getParameter("userId");
		ModelAndView mav = new ModelAndView();
		mav.addObject("userId",userId);
		mav.setViewName("websocket/index");
		return mav;
	}
	
	
	
	
	@RequestMapping(value = "/websocket.do")
	public ModelAndView websocketIndex() {

		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("websocket/index");
		return mav;
	}

	
}
