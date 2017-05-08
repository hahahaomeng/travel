package com.order.bean;
//返回饼状图片数据
public class OrderPicture {
	String name;
	int value;
	public OrderPicture(String name,int value) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.value=value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
}
