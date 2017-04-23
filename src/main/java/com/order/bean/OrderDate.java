package com.order.bean;

public class OrderDate {
	int orderid;
	String state;
	String productname;
	Double price;
	String godata;
	int gonumber;
	String goplace;
	String proplace;
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getGodata() {
		return godata;
	}
	public void setGodata(String godata) {
		this.godata = godata;
	}
	public int getGonumber() {
		return gonumber;
	}
	public void setGonumber(int gonumber) {
		this.gonumber = gonumber;
	}
	public String getGoplace() {
		return goplace;
	}
	public void setGoplace(String goplace) {
		this.goplace = goplace;
	}
	public String getProplace() {
		return proplace;
	}
	public void setProplace(String proplace) {
		this.proplace = proplace;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	
}
