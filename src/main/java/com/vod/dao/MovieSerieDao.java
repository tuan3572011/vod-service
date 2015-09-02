package com.vod.dao;

import java.util.List;

import com.vod.model.MovieSerie;

public interface MovieSerieDao {
	public boolean add(MovieSerie movie);
	public boolean update(MovieSerie movie);
	public boolean remove(MovieSerie movie);
	public MovieSerie getById(Integer id);
	public List<MovieSerie> getAll();
	public List<MovieSerie> getByRate();
	public List<MovieSerie> getByView();
	public List<MovieSerie> getByYear(Integer year);
}
