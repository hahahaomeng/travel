package com.order.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.order.bean.OrderDate;
import com.order.entity.Application;
import com.order.entity.Order;
import com.order.entity.User;
import com.order.service.ApplicationService;
import com.order.service.OrderService;

public class ApplicationAction extends ActionSupport implements ModelDriven<Application> {
	private Application application;
	private ApplicationService applicationService;
	private OrderService orderService;
	@Override
	public Application getModel() {
		// TODO Auto-generated method stub
		return this.application;
	}
	public void setApplicationService(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	/**
	 * 获取用户的状态1退订的申请记录
	 * @throws IOException 
	 */
	public String getRebackApp() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		User user = (User) request.getSession().getAttribute("user");
		List<Application> app=applicationService.findRebackOrder(user);
 		json.accumulate("code", 200);
 		json.accumulate("data", app);
		response.getWriter().print(json.toString());
		return NONE;
	}
}
