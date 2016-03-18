package com.zhuwm.h5.po;

import java.sql.Date;

public class Customer {
	
	private String name;
	private String customerNo;
	private String IDNo;
	private String projectNo;
	private String projectName;
	private Date orderDate;
	
	
	
	public Date getOrderDate() {
		return orderDate;
	}


	
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}


	public String getProjectName() {
		return projectName;
	}

	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCustomerNo() {
		return customerNo;
	}
	
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	
	public String getIDNo() {
		return IDNo;
	}
	
	public void setIDNo(String iDNo) {
		IDNo = iDNo;
	}
	
	public String getProjectNo() {
		return projectNo;
	}
	
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

}
