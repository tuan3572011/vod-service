package com.vod.service;

import java.util.List;

import com.vod.model.MovieOdd;

public interface MovieOddService {
	public List<MovieOdd> getAll(); 
	public MovieOdd get(Integer id);
	public List<MovieOdd> getByView();
	public List<MovieOdd> getByComment();
	public List<MovieOdd> getByRate();
}
