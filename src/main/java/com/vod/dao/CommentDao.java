package com.vod.dao;

import java.util.List;

import com.vod.model.Comment;

public interface CommentDao {
	public boolean add(Comment comment);
	public Comment getById(Integer id);
	public List<Comment> getByUser(String email);
	public List<Comment> getByFilm(Integer filmId);

}
