package com.order.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.order.bean.CommentDate;
import com.order.entity.Comment;
import com.order.entity.Order;
import com.order.entity.User;
import com.order.service.CommentService;
import com.order.service.OrderService;
import com.order.util.UploadFile;

public class CommentAction extends ActionSupport implements ModelDriven<Comment> {
	private Comment comment=new Comment();
	private CommentService commentService;
	private OrderService orderService;
	@Override
	public Comment getModel() {
		// TODO Auto-generated method stub
		return this.comment;
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
	public String addComment() throws IOException{
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json=new JSONObject();
		String path=comment.getCommenturl();
		Comment a=new Comment();
		a.setCommentdate(new Date());
		a.setCommentstate(this.comment.getCommentstate());
		a.setContent(this.comment.getContent());
		Order order=orderService.findOrderByid(Integer.parseInt(request.getParameter("orderid")));
		a.setOrder(order);
		a.setCommenturl(UploadFile.upload(path));
		commentService.addComment(a);
		orderService.comment(order);
		json.accumulate("code", 200);
		json.accumulate("data", "check code is wrong");
		response.getWriter().print(json.toString());
		return NONE;
	}
	/**
	 * 用户查看自己的评论
	 */
	public String checkComment() throws IOException{
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json=new JSONObject();
		User user = (User) request.getSession().getAttribute("user");
		List<Comment> list=commentService.findCommentByUser(user);
		List<CommentDate> list2=new ArrayList();
		for(Comment comment:list){
			if(comment.getOrder().getState().equals("4")){
				CommentDate a=new CommentDate();
				a.setProductname(comment.getOrder().getProduct().getProductname());
				a.setCommentstate(comment.getCommentstate());
				a.setCommenturl(comment.getCommenturl());
				a.setContent(comment.getContent());
				a.setCommentdate(comment.getCommentdate().toString());
				list2.add(a);
			}
		}
		json.accumulate("code", 200);
		json.accumulate("data", list2);
		response.getWriter().print(json.toString());
		return NONE;
	}
}
