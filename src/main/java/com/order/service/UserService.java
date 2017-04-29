package com.order.service;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.order.dao.UserDAO;
import com.order.entity.User;
import com.order.util.MD5;

public class UserService {
	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	/**
	 * 根据用户名查找用户
	 * 
	 * @param userName
	 * @return
	 */
	public User findByUserName(String userName) {
		List user = this.userDAO.findByUsername(userName);
		if (user.size() > 0) {
			return (User) user.get(0);
		}
		return null;
	}
	/**
	 * 保存用户
	 * @param user
	 * @throws Exception
	 */
	public void save(User user) throws Exception {
		// TODO Auto-generated method stub
		user.setType("0");//0表示用户，1表示管理员
		user.setState("0");//0表示正常，1表示冻结
		String password = user.getPassword();
		user.setPassword(MD5.encoderByMd5(password));
		ActionContext ac = ActionContext.getContext();
		String code = (String) ac.getSession().get("checkCode");
		user.setCheckcode(code);
		this.userDAO.save(user);
	}
	/**
	 * 查找所有用户
	 * @return
	 */
	public List<User> findAllUser() {
		List<User> list = this.userDAO.findAll();
		return list;
	}
	/**
	 *根据id查找用户
	 * @param userid
	 * @return
	 */
	public User findByUserId(Integer userid) {
		// TODO Auto-generated method stub
		User user=userDAO.findById(userid);
		return user;
	}
	/**
	 * 更新用户
	 * @param user2
	 */
	public void update(User user2) {
		// TODO Auto-generated method stub
		userDAO.attachDirty(user2);
	}
	public void freezer(User user) {
		// TODO Auto-generated method stub
		user.setState("1");
		userDAO.attachDirty(user);
	}
	public void nofreezer(User user) {
		// TODO Auto-generated method stub
		user.setState("0");
		userDAO.attachDirty(user);
	}
}
