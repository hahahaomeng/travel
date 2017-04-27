package com.order.service;

import java.util.ArrayList;
import java.util.List;

import com.order.dao.CommentDAO;
import com.order.entity.Comment;
import com.order.entity.User;

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
	public List<Comment> findCommentByUser(User user) {
		// TODO Auto-generated method stub
		List<Comment> list=commentDAO.findAll();
		List<Comment> list2=new ArrayList();
		for(Comment comment:list){
			if(comment.getOrder().getUser().getUserid()==user.getUserid()){
				list2.add(comment);
			}
		}
		return list2;
	}
}
