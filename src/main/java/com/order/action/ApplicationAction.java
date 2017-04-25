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
import com.order.bean.ApplicationDate;
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
	 * 获取用户退订的申请记录
	 * @throws IOException 
	 */
	public String getRebackApp() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		User user = (User) request.getSession().getAttribute("user");
		List<Application> app=applicationService.findRebackOrder(user);
		List<ApplicationDate> list=new ArrayList();
		for(Application application:app){
			ApplicationDate a=new ApplicationDate();
			a.setAppid(application.getAppid());
			a.setAppnotice(application.getAppnotice());
			a.setApproderid(application.getOrder().getOrderid());
			if(application.getAppstate().equals("0")){
				a.setAppstate("申请中");
			}else if(application.getAppstate().equals("1")){
				a.setAppstate("申请通过");
			}else{
				a.setAppstate("未通过");
			}
			a.setApptype("退订");
			a.setGonumber(application.getOrder().getGonumber());
			a.setPrice(application.getOrder().getPrice());
			a.setProductname(application.getOrder().getProduct().getProductname());
			a.setAppdate(application.getAppdate().toString());
			list.add(a);
		}
 		json.accumulate("code", 200);
 		json.accumulate("data", list);
		response.getWriter().print(json.toString());
		return NONE;
	}
}
