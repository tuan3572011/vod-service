package com.vod.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vod.dao.MovieDao;
import com.vod.model.Category;
import com.vod.model.Country;
import com.vod.model.Movie;
import com.vod.service.MovieService;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {
	@Autowired
	private MovieDao dao;

	@Override
	public boolean add(Movie movie) {
		return dao.add(movie);
	}

	@Override
	public boolean update(Movie movie) {
		return dao.update(movie);
	}

	@Override
	public Movie get(Integer id) {
		return dao.get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vod.service.MovieService#filterBy(java.lang.Integer,
	 * java.lang.Integer, java.lang.Integer, java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public List<Movie> filterBy(Integer orderId, Category category, Integer year, Country country, Integer page) {
		return dao.filterBy(orderId, category, year, country, page);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vod.service.MovieService#getFilterPage(java.lang.Integer,
	 * com.vod.model.Category, java.lang.Integer, com.vod.model.Country,
	 * java.lang.Integer)
	 */
	@Override
	public int getFilterTotalPage(Integer orderId, Category category, Integer year, Country country, Integer page) {
		return dao.getFilterPage(orderId, category, year, country, page);
	}

}
