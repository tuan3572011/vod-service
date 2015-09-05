package com.vod.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vod.dao.MovieSerieDao;
import com.vod.model.Category;
import com.vod.model.Country;
import com.vod.model.Movie;
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
	public List<Movie> filterBy(Integer orderId, Category category, Integer year, Country country, Integer page) {
		return dao.filterBy(orderId, category, year, country, page);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vod.service.MovieSerieService#getFilterPage(java.lang.Integer,
	 * com.vod.model.Category, java.lang.Integer, com.vod.model.Country,
	 * java.lang.Integer)
	 */
	@Override
	public Integer getFilterTotalPage(Integer orderId, Category category, Integer year, Country country, Integer page) {
		return dao.getFilterPage(orderId, category, year, country, page);
	}

}
