package com.vod.dao;

import java.util.List;

import com.vod.model.Category;
import com.vod.model.Country;
import com.vod.model.Movie;
import com.vod.model.MovieSerie;

public interface MovieSerieDao {
	public boolean add(MovieSerie movie);

	public boolean update(MovieSerie movie);

	public boolean remove(MovieSerie movie);

	public MovieSerie getById(Integer id);

	List<Movie> filterBy(Integer orderId, Category category, Integer year, Country country, Integer page);

	/**
	 * @param orderId
	 * @param category
	 * @param year
	 * @param country
	 * @param page
	 * @return
	 */
	public Integer getFilterPage(Integer orderId, Category category, Integer year, Country country, Integer page);
}
