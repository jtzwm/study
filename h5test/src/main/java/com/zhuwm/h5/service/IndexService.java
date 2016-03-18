package com.zhuwm.h5.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zhuwm.h5.po.Customer;

@Service
public class IndexService {

	public List queryCustomeList() {
		ArrayList<Customer> resultList=new ArrayList();
		Customer c1=new Customer();
		c1.setCustomerNo("1");
		c1.setIDNo("1234");
		c1.setName("fuck freemarker");
		c1.setProjectNo("100万");
		c1.setProjectName("NB1号产品");
		c1.setOrderDate(new Date(System.currentTimeMillis()));
		
		Customer c2=new Customer();
		c2.setCustomerNo("2");
		c2.setIDNo("2234");
		c2.setName("fuck spring mvc");
		c2.setProjectNo("200万");		
		c2.setProjectName("NB2号产品");
		c2.setOrderDate(new Date(System.currentTimeMillis()));
		
		resultList.add(c1);
		resultList.add(c2);
		return resultList;
	}

}
