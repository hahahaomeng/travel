package com.order.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.order.entity.User;
import com.order.service.UserService;
import com.order.util.CreateUUID;
import com.order.util.MD5;
import com.order.util.Mail;

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
		System.out.println(isCheckEmail);
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
	/**
	 * 邮箱验证
	 * 
	 * @return
	 * @throws IOException
	 */
	public String checkCode() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		String code = (String) ServletActionContext.getRequest().getSession()
				.getAttribute("checkCode");
		if (user.getCheckcode().equals(code)) {
			json.accumulate("code", 200);
			ServletActionContext.getRequest().getSession()
					.setAttribute("checkEmail", "true");
		} else {
			json.accumulate("code", 300);
			json.accumulate("errMsg", "check code is wrong");
		}
		response.getWriter().print(json.toString());
		return NONE;
	}
	

	/**
	 * 发送激活邮件
	 * 
	 * @return
	 * @throws IOException
	 */
	public String sendEmail() throws IOException {
		String checkCode = CreateUUID.createUUID().toString().substring(0, 6);
		System.out.println(user.getEmail());
		Mail.send(user.getEmail(), checkCode);
		ActionContext ac = ActionContext.getContext();
		ac.getSession().put("checkCode", checkCode);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		json.accumulate("code", 200);
		out.println(json.toString());
		out.flush();
		out.close();
		return NONE;
	}

	/**
	 * 判断邮箱是否存在
	 * 
	 * @throws IOException
	 */
	public String checkEmail() throws IOException {
		// 查找所有用户
		// System.out.println(user.getEmail());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		List<User> list2 = this.userService.findAllUser();
		String email = user.getEmail();
		// System.out.println(list2.size());
		int j = 0;
		for (User a : list2) {
			if (a.getEmail().equals(email) == true) {
				j = 1;
			}
		}
		if (j == 1) {
			json.accumulate("code", 300);
			json.accumulate("errMsg", "该邮箱已经注册");
		} else {
			json.accumulate("code", 200);
		}
		out.println(json.toString());
		out.flush();
		out.close();
		return NONE;
	}

	/**
	 * 获取个人信息
	 * 
	 * @throws IOException
	 */
	public String getPersonInfo() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		User user1 = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		JSONObject json = new JSONObject();
		json.accumulate("code", 200);
		json.accumulate("data", userService.findByUserId(user1.getUserid()));
		response.getWriter().println(json.toString());
		return NONE;
	}
	/**
	 * 注销
	 */
	public String logout() throws IOException{
		ServletActionContext.getRequest().getSession()
		.setAttribute("user", null);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		json.accumulate("code", 200);
		json.accumulate("data", null);
		response.getWriter().println(json.toString());
		return NONE;
	}
	/**
	 * 修改个人信息
	 * @throws IOException 
	 */
	public String modifyUserInfo() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		User user1 = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		User user2=userService.findByUserId(user1.getUserid());
		user2.setPhone(user.getPhone());
		user2.setUsername(user.getUsername());
		userService.update(user2);
		JSONObject json = new JSONObject();
		json.accumulate("code", 200);
		json.accumulate("data", null);
		response.getWriter().println(json.toString());
		return NONE;
	}
	/**
	 * 忘记密码邮件
	 */
	public String modPasswordEmail() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		User user1 = userService.findByUserName(user.getUsername());
		//System.out.println(user1);
		String checkCode = CreateUUID.createUUID().toString().substring(0, 6);
		Mail.send(user1.getEmail(), checkCode);
		ServletActionContext.getRequest().getSession().setAttribute("username", user.getUsername());
		ServletActionContext.getRequest().getSession().setAttribute("checkCode", checkCode);
		JSONObject json = new JSONObject();
		json.accumulate("code", 200);
		response.getWriter().print(json.toString());
		return NONE;
	}
	/**
	 * 修改密码
	 */
	public String modPassword() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		String isCheckEmail = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("checkEmail");
		String username = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("username");
		if (null == isCheckEmail || !"true".equals(isCheckEmail)) {
			json.accumulate("code", 500);
			json.accumulate("errMsg", "csrf");
			response.getWriter().print(json.toString());
		} else {
			User user1 = userService.findByUserName(username);
			user1.setPassword(MD5.encoderByMd5(user.getPassword()));
			userService.update(user1);
			json.accumulate("code", 200);
			response.getWriter().print(json.toString());
		}
		return NONE;
	}
}
