package com.order.service;

import com.order.dao.CommentDAO;
import com.order.entity.Comment;

public class CommentService {
	private CommentDAO commentDAO;
	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}
	/**
	 * 用户添加评论
	 * @param comment
	 */
	public void addComment(Comment comment) {
		// TODO Auto-generated method stub
		commentDAO.save(comment);
	}
}
