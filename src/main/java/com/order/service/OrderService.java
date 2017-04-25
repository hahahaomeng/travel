package com.order.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.order.bean.OrderDate;
import com.order.dao.OrderDAO;
import com.order.entity.Order;
import com.order.entity.User;

public class OrderService {
	private OrderDAO orderDAO;
	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
	/**
	 * 用户下订单
	 */
	public void addOrder(Order order){
		orderDAO.save(order);
	}
	/**
	 * 根据id查找order
	 */
	public Order findOrderByid(int orderid){
		return orderDAO.findById(orderid);
	}
	/**
	 * 用户付款
	 */
	public void payOrder(Order order){
		order.setState("1");
		orderDAO.attachDirty(order);
	}
	/**
	 * 根据用户查找order
	 */
	public List<OrderDate> findOrdByUser(User user){
		List<Order> list= orderDAO.findByProperty("user", user);
		List<OrderDate> list2=new ArrayList<OrderDate>();
		for(Order order:list){
			OrderDate orderDate=new OrderDate();
			orderDate.setProductname(order.getProduct().getProductname());
			orderDate.setProplace(order.getProduct().getProplace());
			orderDate.setPrice(order.getPrice());
			orderDate.setGoplace(order.getProduct().getGoplace());
			orderDate.setGonumber(order.getGonumber());
			orderDate.setGodata(order.getGodate().toString());
			orderDate.setState(order.getState());
			orderDate.setOrderid(order.getOrderid());
			list2.add(orderDate);
		}
		return list2;
	}
	public void deleteOrder(Order order) {
		// TODO Auto-generated method stub
		orderDAO.delete(order);
	}
	public void endOrder(Order order) {
		// TODO Auto-generated method stub
		order.setState("2");
		orderDAO.attachDirty(order);
	}
	public void reback(Order order) {
		// TODO Auto-generated method stub
		order.setState("3");
		orderDAO.attachDirty(order);
	}
}	
