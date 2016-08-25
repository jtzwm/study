package com.zhuwm.h5.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import com.zhuwm.h5.service.IndexService;
import com.zhuwm.h5.websocket.WebScoketServerAdvisor;
import com.zhuwm.redis.OnLineUserImpl;


@Controller
public class OnlineUserController  extends DispatcherServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IndexService indexService;


	@RequestMapping(value = "/onLineUser.do")
	public ModelAndView getOnLineUserList() {
		
		OnLineUserImpl impl = new OnLineUserImpl();
		long userCount=impl.getQueueCount();		
		List<String> userList=impl.getQueueUserList();
		impl.releaseJedis();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("userCount",userCount);
		mav.addObject("resultList",userList);
		mav.setViewName("websocket/onLineUser");
		return mav;
	}
	
	@RequestMapping(value = "/sendMessage.do")
	public ModelAndView sendMessage(HttpServletRequest request, HttpServletResponse response) {
		
		WebScoketServerAdvisor.sendMessageToAll(request.getParameter("m"));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("resultInfo","成功");
		mav.setViewName("websocket/result");
		return mav;
	}
	
	@RequestMapping(value = "/deleteUser.do")
	public String deleteUser(HttpServletRequest request, HttpServletResponse response) {
		String userId=request.getParameter("userID");
		OnLineUserImpl impl = new OnLineUserImpl();
		impl.removeFromeQueue(userId);
		//long userCount=impl.getQueueCount();		
		//List<String> userList=impl.getQueueUserList();
		impl.releaseJedis();
		
		return "redirect:/onLineUser.do";
	}	

	
}
