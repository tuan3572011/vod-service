package com.vod.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vod.dao.MovieSerieDao;
import com.vod.model.MovieSerie;
import com.vod.service.MovieSerieService;

@Service
@Transactional
public class MovieSerieServiceImpl implements MovieSerieService {
	@Autowired
	private MovieSerieDao dao;

	@Override
	public boolean add(MovieSerie movie) {
		// TODO Auto-generated method stub
		return dao.add(movie);
	}

	@Override
	public boolean update(MovieSerie movie) {
		// TODO Auto-generated method stub
		return dao.update(movie);
	}

	@Override
	public boolean remove(MovieSerie movie) {
		// TODO Auto-generated method stub
		return dao.remove(movie);
	}

	@Override
	public MovieSerie getById(Integer id) {
		// TODO Auto-generated method stub
		return dao.getById(id);
	}

	@Override
	public List<MovieSerie> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public List<MovieSerie> getByRate() {
		return dao.getByRate();
	}

	@Override
	public List<MovieSerie> getByView() {
		return dao.getByView();
	}

	@Override
	public List<MovieSerie> getByYear(Integer year) {
		return dao.getByYear(year);
	}

}
