package com.order.action;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.order.entity.Comment;
import com.order.entity.Order;
import com.order.service.CommentService;
import com.order.service.OrderService;

public class CommentAction extends ActionSupport implements ModelDriven<Comment> {
	private Comment comment;
	private CommentService commentService;
	private OrderService orderService;
	@Override
	public Comment getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	/**
	 * 添加评论
	 */
	public void addComment() throws IOException{
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("text/html);charset=UTF-8");
		JSONObject json=new JSONObject();
		Order order=this.comment.getOrder();
		System.out.println(order);
		System.out.println(this.comment.getCommentstate());
		//this.comment.setCommentdate(new Date());
		//commentService.addComment(this.comment);
		json.accumulate("code", 200);
		json.accumulate("data", null);
		response.getWriter().print(json.toString());
	}

}
