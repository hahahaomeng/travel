package com.order.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.order.entity.Comment;
import com.order.service.CommentService;

public class CommentAction extends ActionSupport implements ModelDriven<Comment> {
	private Comment comment;
	private CommentService commentService;
	@Override
	public Comment getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	

}
