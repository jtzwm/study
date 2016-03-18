package com.zhuwm.ssm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zhuwm.ssm.bo.Items;


@Controller
public class ItemsController2  {

	
	@RequestMapping("/queryItems2.action")
	public ModelAndView queryItems() throws Exception {
		
		List<Items> itemsList = new ArrayList<Items>();
		Items items_1 = new Items();
		items_1.setId(1);
		items_1.setName("联想笔记本");
		items_1.setPrice(6000f);
		items_1.setDetail("ThinkPad T430 联想笔记本电脑！");
		
		Items items_2 = new Items();
		items_2.setId(2);
		items_2.setName("苹果手机");
		items_2.setPrice(5000f);
		items_2.setDetail("iphone6苹果手机！");
		
		itemsList.add(items_1);
		itemsList.add(items_2);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemList",itemsList);
		modelAndView.setViewName("items/itemsList");

		return modelAndView;
	}
	
	@RequestMapping("/itemEdit.action")
	public ModelAndView itemEdit(@ModelAttribute("pojo") Items items) throws Exception{
		
		System.out.println(items.getId());
		
		Items items_1 = new Items();
		items_1.setName("联想笔记本");
		items_1.setPrice(6000f);
		items_1.setDetail("ThinkPad T430 联想笔记本电脑！");
		
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("item",items_1);
		modelAndView.setViewName("items/itemEdit");

		return modelAndView;
	}

}
