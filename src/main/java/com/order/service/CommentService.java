package com.order.service;

import com.order.dao.CommentDAO;

public class CommentService {
	private CommentDAO commentDAO;
	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}
}
