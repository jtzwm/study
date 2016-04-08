package com.zhuwm.h5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import com.zhuwm.h5.service.IndexService;
import com.zhuwm.redis.OnLineUserImpl;


@Controller
public class OnlineUserController  extends DispatcherServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IndexService indexService;


	@RequestMapping(value = "/onLineUser.do")
	public ModelAndView preengageList() {
		
		OnLineUserImpl impl = new OnLineUserImpl();
		long userCount=impl.getQueueCount();
		List<String> userList=impl.getQueueUserList();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("userCount",userCount);
		mav.addObject("resultList",userList);
		mav.setViewName("websocket/onLineUser");
		return mav;
	}

	
}
