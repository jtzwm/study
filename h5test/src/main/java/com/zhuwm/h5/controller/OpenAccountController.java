package com.zhuwm.h5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import com.zhuwm.h5.service.IndexService;


@Controller
public class OpenAccountController  extends DispatcherServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IndexService indexService;


	@RequestMapping(value = "/openAccount.do")
	public ModelAndView preengageList() {
		List CustomeList=indexService.queryCustomeList();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("resultList",CustomeList);
		//mav.addObject("customerNo", "fuck freemarker");
		mav.setViewName("f7test/openAccount");
		return mav;
	}

	
}
