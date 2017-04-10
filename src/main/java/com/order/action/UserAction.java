package com.order.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.order.entity.User;
import com.order.service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	private User user = new User();
	private UserService userService;
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return this.user;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	/**
	 * 检测用户名是否存在
	 * 
	 * @throws IOException
	 */
	public String checkUserName() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		User existUser = this.userService.findByUserName(this.user
				.getUsername());
		if (existUser == null) {
			json.accumulate("code", 200);
			json.accumulate("errMsg", "username is not exist");
		} else {
			json.accumulate("code", 300);
			json.accumulate("errMsg", "username is exist");
		}
		out.println(json.toString());
		out.flush();
		out.close();
		return NONE;
	}
	/**
	 * 
	 */
}
