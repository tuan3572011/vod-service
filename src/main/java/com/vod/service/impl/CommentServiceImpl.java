package com.vod.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vod.dao.CommentDao;
import com.vod.model.Comment;
import com.vod.service.CommentService;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDao dao;

	@Override
	public boolean add(Comment comment) {
		return dao.add(comment);
	}

	@Override
	public Comment getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public List<Comment> getByUser(String email) {
		return dao.getByUser(email);
	}

	@Override
	public List<Comment> getByFilm(Integer filmId) {
		return dao.getByFilm(filmId);
	}

}
