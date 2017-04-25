package com.order.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.order.bean.OrderDate;
import com.order.entity.Order;
import com.order.entity.Product;
import com.order.entity.User;
import com.order.service.OrderService;
import com.order.service.ProductService;

public class OrderAction extends ActionSupport implements ModelDriven<Order> {
	private Order order;
	private ProductService productService;
	private OrderService orderService;
	@Override
	public Order getModel() {
		// TODO Auto-generated method stub
		return this.order;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	//用户下订单
	public String applyOrder() throws IOException, ParseException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		User user = (User) request.getSession().getAttribute("user");
		Order order1=new Order();
		order1.setUser(user);
		Product product=productService.findProductById(Integer.parseInt(request.getParameter("productid")));
		order1.setProduct(product);
		order1.setPaydate(new Date());
		//System.out.println(request.getParameter("godate").substring(0, 10));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(request.getParameter("godate").substring(0, 10));
		order1.setGodate(date);
		order1.setPrice(Double.parseDouble(request.getParameter("price"))*Integer.parseInt(request.getParameter("gonumber")));
		order1.setState("0");
		order1.setGonumber(Integer.parseInt(request.getParameter("gonumber")));
		order1.setPaydate(new Date());
		orderService.addOrder(order1);
		json.accumulate("code", 200);
		json.accumulate("data", order1);
		response.getWriter().print(json.toString());
		return NONE;
	}
	/**
	 * 用户付款
	 * @throws IOException 
	 */
	public String payOrder() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		Order order=orderService.findOrderByid(Integer.parseInt(request.getParameter("orderid")));
		orderService.payOrder(order);
		json.accumulate("code", 200);
		response.getWriter().print(json.toString());
		return NONE;
	}
	/**
	 * 用户查看所有订单
	 * @throws IOException 
	 */
	public String findAllOrder() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		User user = (User) request.getSession().getAttribute("user");
		//System.out.println(user);
		List<OrderDate> orders=orderService.findOrdByUser(user);
		json.accumulate("code", 200);
		json.accumulate("data", orders);
		response.getWriter().print(json.toString());
		return NONE;
	}
	/**
	 * 根据orderid查找order
	 */
	public String findOrderById() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		String orderid=request.getParameter("orderid");
		//System.out.println(orderid);
		Order order=orderService.findOrderByid(Integer.parseInt(orderid));
		OrderDate orderDate=new OrderDate();
		orderDate.setProductname(order.getProduct().getProductname());
		orderDate.setProplace(order.getProduct().getProplace());
		orderDate.setPrice(order.getPrice());
		orderDate.setGoplace(order.getProduct().getGoplace());
		orderDate.setGonumber(order.getGonumber());
		orderDate.setGodata(order.getGodate().toString());
		orderDate.setState(order.getState());
		//System.out.println(order.getOrderid());
		orderDate.setOrderid(order.getOrderid());
		//System.out.println(orderDate);
		json.accumulate("code", 200);
		json.accumulate("data", orderDate);
		response.getWriter().print(json.toString());
		return NONE;
	}
	/**
	 * 用户查看未付款订单
	 */
	public String findnoPayOrder() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		User user = (User) request.getSession().getAttribute("user");
		//System.out.println(user);
		List<OrderDate> orders=orderService.findOrdByUser(user);
		List<OrderDate> orders1=new ArrayList<OrderDate>();
		for(OrderDate orderDate:orders){
			if(orderDate.getState().equals("0")){
				orders1.add(orderDate);
			}
		}
 		json.accumulate("code", 200);
		json.accumulate("data", orders1);
		response.getWriter().print(json.toString());
		return NONE;
	}
	
	/**
	 * 用户查看已付款订单
	 */
	public String findPayOrder() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		User user = (User) request.getSession().getAttribute("user");
		//System.out.println(user);
		List<OrderDate> orders=orderService.findOrdByUser(user);
		List<OrderDate> orders1=new ArrayList<OrderDate>();
		for(OrderDate orderDate:orders){
			if(orderDate.getState().equals("1")){
				orders1.add(orderDate);
			}
		}
//		System.out.println(orders1.size());
 		json.accumulate("code", 200);
		json.accumulate("data", orders1);
		response.getWriter().print(json.toString());
		return NONE;
	}
	/**
	 * 用户查看已完成订单
	 * @throws IOException 
	 */
	public String finishOrder() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		User user = (User) request.getSession().getAttribute("user");
		//System.out.println(user);
		List<OrderDate> orders=orderService.findOrdByUser(user);
		List<OrderDate> orders1=new ArrayList<OrderDate>();
		for(OrderDate orderDate:orders){
			if(orderDate.getState().equals("2")){
				orders1.add(orderDate);
			}
		}
//		System.out.println(orders1.size());
 		json.accumulate("code", 200);
		json.accumulate("data", orders1);
		response.getWriter().print(json.toString());
		return NONE;
	}
	/**
	 * 完成订单
	 * @throws IOException 
	 */
	public String endOrder() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		String orderid=request.getParameter("orderid");
		Order order=orderService.findOrderByid(Integer.parseInt(orderid));
		//System.out.println(order);
		orderService.endOrder(order);
		json.accumulate("code", 200);
		response.getWriter().print(json.toString());
		return NONE;
	}
	
	/**
	 * 删除订单
	 * @throws IOException 
	 */
	public String deleteOrder() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		Order order=orderService.findOrderByid(Integer.parseInt(request.getParameter("orderid")));
		orderService.deleteOrder(order);
		json.accumulate("code", 200);
		response.getWriter().print(json.toString());
		return NONE;
	}
	/**
	 * 用户退订
	 */
	public String reback() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		String orderid=request.getParameter("orderid");
		Order order=orderService.findOrderByid(Integer.parseInt(orderid));
		//System.out.println(order);
		orderService.reback(order);
		json.accumulate("code", 200);
		response.getWriter().print(json.toString());
		return NONE;
	}
}
