package com.vod.service;

import java.util.List;

import com.vod.model.Director;
import com.vod.model.Film;
import com.vod.model.MovieSearch;

public interface SearchService {
	public List<MovieSearch> search(String type, String data);
	public Film getFilmById(Integer movieId);
	public List<Film> getByCategory(String cate);
	public List<Film> allFilm();
	public List<Director> allDirector();
	public List<MovieSearch> topIMDB();
	public List<MovieSearch> topNew();
}
