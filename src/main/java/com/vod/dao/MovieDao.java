package com.vod.dao;

import java.util.List;

import com.vod.model.Category;
import com.vod.model.Movie;

public interface MovieDao {
	public boolean add(Movie movie);

	public boolean update(Movie movie);

	public Movie get(Integer id);

	public List<Movie> getAll();

	public List<Movie> getByCategory(String category);

	public List<Movie> getByYear(Integer year);

	public List<Movie> getByCountry(String country);

	public List<Movie> getByRate();

	public List<Movie> getByView();

	/**
	 * @param orderId
	 * @param categoryId
	 * @param year
	 * @param countryId
	 * @param page
	 * @return
	 */
	List<Movie> filterBy(Integer orderId, Category category, Integer year, Integer countryId, Integer page);
}
