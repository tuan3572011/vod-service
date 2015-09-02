package com.vod.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vod.dao.MovieOddDao;
import com.vod.model.MovieOdd;
import com.vod.service.MovieOddService;

@Service
@Transactional
public class MovieOddServiceImpl implements MovieOddService {
	@Autowired
	private MovieOddDao dao;

	@Override
	public List<MovieOdd> getAll() {
		return dao.getAll();
	}

	@Override
	public List<MovieOdd> getByView() {
		return dao.getByView();
	}

	@Override
	public List<MovieOdd> getByComment() {
		return dao.getByComment();
	}

	@Override
	public List<MovieOdd> getByRate() {
		return dao.getByRate();
	}

	@Override
	public MovieOdd get(Integer id) {
		return dao.get(id);
	}

}
