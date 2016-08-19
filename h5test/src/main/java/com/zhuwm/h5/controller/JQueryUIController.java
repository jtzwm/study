package com.zhuwm.h5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import com.zhuwm.h5.service.IndexService;


@Controller
public class JQueryUIController  extends DispatcherServlet {

	private static final long serialVersionUID = 1L;


	@RequestMapping(value = "/jqueryUI.do")
	public ModelAndView showIndex() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jqueryUI");
		return mav;
	}
	
	
}
