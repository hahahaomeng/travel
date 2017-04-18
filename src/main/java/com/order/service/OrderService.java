package com.order.service;

import com.order.dao.OrderDAO;
import com.order.entity.Order;

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
}	
