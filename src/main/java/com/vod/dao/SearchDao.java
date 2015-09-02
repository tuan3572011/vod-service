package com.vod.dao;

import java.util.List;

import com.vod.model.Director;
import com.vod.model.Film;
import com.vod.model.MovieSearch;

public interface SearchDao {
	public List<MovieSearch> search(String type, String data);
	public Film getFilmById(Integer movieId);
	public List<Film> allFilm();
	public List<Film> getByCategory(String cate);
	public List<Director> allDirector();
	public List<MovieSearch> topIMDB();
	public List<MovieSearch> topNew();
}
