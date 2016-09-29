package com.zhuwm.weixin.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.RequestPartServletServerHttpRequest;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import com.zhuwm.weixin.service.GroupMsgService;

@Controller
public class SendMsgController extends DispatcherServlet {
	
	@Autowired
	GroupMsgService groupMsgService;

	@RequestMapping(value = "/sendMsg.do")
	public ModelAndView sendMsg(HttpServletRequest request, HttpServletResponse response) {
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
		System.out.println("===收到ajax请求："+request.getParameter("msg"));
		String type=request.getParameter("type");
		groupMsgService.setType(type);
		groupMsgService.sendMsgToAll();
		ArrayList<String> resultList= new ArrayList<String>();
		resultList.add("success");
		return resultList;

	}

}
