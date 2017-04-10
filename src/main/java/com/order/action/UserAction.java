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
import com.order.util.MD5;

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
	 * 用户注册
	 */
	public String register() throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html);charset=UTF-8");
		JSONObject json=new JSONObject();
		String isCheckEmail = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("checkEmail");
		if (null == isCheckEmail || !"true".equals(isCheckEmail)) {
			json.accumulate("code", 500);
			json.accumulate("errMsg", "邮箱错误");
			response.getWriter().print(json.toString());
		} else {
			this.userService.save(this.user);
			json.accumulate("code", 200);
			json.accumulate("data", null);
			response.getWriter().print(json.toString());
		}
		return NONE;
	}
	/**
	 * 用户登录
	 */
	public String login() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		String pass=MD5.encoderByMd5(this.user.getPassword());
		User user1=userService.findByUserName(this.user.getUsername());
		if (user1!=null&&pass.equals(user1.getPassword())) {
			json.accumulate("code", 200);
			json.accumulate("data", user1);
			ServletActionContext.getRequest().getSession().setAttribute("user", user1);
		} else {
			json.accumulate("code", 300);
			json.accumulate("errMsg", "username or password is wrong");
		}
		out.println(json.toString());
		out.flush();
		out.close();
		return NONE;
	}
}
