package com.example.demo.repository;

import java.util.List;

import com.example.demo.entities.Comment;

public interface CommentDAO {
	public List<Comment> getComments(int idProduct);

	public boolean insertComment(Comment comment);

	public boolean deleteComment(int idComment);
}
