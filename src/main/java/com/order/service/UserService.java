package com.order.service;

import java.util.List;

import com.order.dao.UserDAO;
import com.order.entity.User;

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
}
