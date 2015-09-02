package com.vod.service;

import java.util.List;

import com.vod.model.Comment;

public interface CommentService {
	public boolean add(Comment comment);
	public Comment getById(Integer id);
	public List<Comment> getByUser(String email);
	public List<Comment> getByFilm(Integer filmId);
}
