package com.zhuwm.h5.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import com.zhuwm.h5.service.FlowService;
import com.zhuwm.h5.service.IndexService;


@Controller
public class FlowController  extends DispatcherServlet {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	FlowService flowService;


	@RequestMapping(value = "/flowController.do")
	public String flowController(HttpServletRequest request) {
		String bizCode=request.getParameter("bizCode");
		String flowType=request.getParameter("flowType");
		String nextUrl=flowService.getNextCodeUrl(bizCode,flowType);
		System.out.println("===通过流程控制器，获取bizCode:"+bizCode+",下一节点url为："+nextUrl);
		return "redirect:/"+nextUrl+"?bizCode="+bizCode+"&flowType="+flowType;
		
	}
	
	@RequestMapping(value = "/appoint.do")
	public ModelAndView appointController(HttpServletRequest request) {
		String bizCode=request.getParameter("bizCode");
		System.out.println("===redirect，获取bizCode:"+bizCode);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("appoint");
		return mav;
	}
	
	@RequestMapping(value = "/info.do")
	public ModelAndView infoController(HttpServletRequest request) {
		String bizCode=request.getParameter("bizCode");
		System.out.println("===redirect，获取bizCode:"+bizCode);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("info");
		return mav;
	}
	
}
