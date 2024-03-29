package com.zhuwm.h5.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import com.zhuwm.h5.service.IndexService;


@Controller
public class IndexController  extends DispatcherServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IndexService indexService;


	@RequestMapping(value = "/f7index.do")
	public ModelAndView preengageList(HttpServletRequest request) {
		
		//从微信跳转过来的，有openid。这个opendid是自己加到链接上的，不是微信平台传过来的
		String openID=request.getParameter("openid");
		System.out.println("======openid="+openID);
		List CustomeList=indexService.queryCustomeList();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("resultList",CustomeList);
		
		//mav.addObject("customerNo", "fuck freemarker");
		mav.setViewName("f7test/index");
		return mav;
	}
	
	@RequestMapping(value = "/index.do")
	public ModelAndView showIndex() {
		

		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}
	
	@ResponseBody  
	@RequestMapping(value = "/json.do")
	public List<String> jsonList() {
		List CustomeList=indexService.queryCustomeList();
		return CustomeList;
	}
	
	
	@RequestMapping(value = "/butelTestClient.do")
	public ModelAndView butelTestClient() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("butelClient");
		return mav;
	}

	
	@RequestMapping(value = "/butelTestServer.do")
	public ModelAndView butelTestServer() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("butelServer");
		return mav;
	}
	

	
}
