package com.zhuwm.springtest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	private static final Logger LOG = Logger.getLogger("LoginController");

	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("index");

	}

	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("index");

	}
	
	
	@RequestMapping("/h5")
	public ModelAndView h5(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("h5");
		System.out.println("h5");
		return new ModelAndView("index");

	}
}