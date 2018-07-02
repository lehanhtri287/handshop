package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Comment;
import com.example.demo.repository.CommentDAO;
import com.example.demo.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentDAO commentDAO;
	
	@Override
	public List<Comment> getComments(int idProduct) {
		return commentDAO.getComments(idProduct);
	}

	@Override
	public boolean insertComment(Comment comment) {
		return commentDAO.insertComment(comment);
	}

	@Override
	public boolean deleteComment(int idComment) {
		return commentDAO.deleteComment(idComment);
	}

}
