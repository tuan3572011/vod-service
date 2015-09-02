package com.vod.dao;

import java.util.List;

import com.vod.model.MovieOdd;

public interface MovieOddDao {
	public List<MovieOdd> getAll();
	public MovieOdd get(Integer id);
	public List<MovieOdd> getByView();
	public List<MovieOdd> getByComment();
	public List<MovieOdd> getByRate();
}
