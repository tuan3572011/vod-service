package com.vod.service;

import java.util.List;

import com.vod.model.Category;
import com.vod.model.Country;
import com.vod.model.Movie;
import com.vod.model.MovieSerie;

public interface MovieSerieService {
	public boolean add(MovieSerie movie);

	public boolean update(MovieSerie movie);

	public boolean remove(MovieSerie movie);

	public MovieSerie getById(Integer id);

	public List<MovieSerie> getAll();

	public List<Movie> filterBy(Integer orderId, Category category, Integer year, Country country, Integer page);
}
