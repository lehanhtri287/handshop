package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.Comment;

public interface CommentService {
	public List<Comment> getComments(int idProduct);

	public boolean insertComment(Comment comment);

	public boolean deleteComment(int idComment);
}
