package com.order.service;

import java.util.ArrayList;
import java.util.List;

import com.order.dao.ApplicationDAO;
import com.order.entity.Application;
import com.order.entity.User;

public class ApplicationService {
	private ApplicationDAO applicationDAO;
	public void setApplicationDAO(ApplicationDAO applicationDAO) {
		this.applicationDAO = applicationDAO;
	}
	/**
	 * 用户申请
	 */
	public void addApplication(Application application){
		applicationDAO.save(application);
	}
	public List<Application> findRebackOrder(User user) {
		// TODO Auto-generated method stub
		List<Application> list=applicationDAO.findAll();
		List<Application> list2=new ArrayList();
		for(Application application:list){
			if((application.getOrder().getUser().getUserid()==user.getUserid())&&(application.getOrder().getState().equals("3"))){
				list2.add(application);
			}
		}
		return list2;
	}
	public List<Application> findAll() {
		// TODO Auto-generated method stub
		return applicationDAO.findAll();
	}
	public Application findByAppid(int parseInt) {
		// TODO Auto-generated method stub
		return applicationDAO.findById(parseInt);
	}
	public void passApp(Application app) {
		// TODO Auto-generated method stub
		app.setAppstate("1");
		applicationDAO.attachDirty(app);
	}
	public void rejectApp(Application app) {
		// TODO Auto-generated method stub
		app.setAppstate("2");
		applicationDAO.attachDirty(app);
	}
}
