package com.vod.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vod.dao.SearchDao;
import com.vod.model.Director;
import com.vod.model.Film;
import com.vod.model.MovieSearch;
import com.vod.service.SearchService;

@Service
@Transactional
public class SearchServiceImpl implements SearchService {
	@Autowired
	private SearchDao dao;

	@Override
	public List<MovieSearch> search(String type, String data) {
		return dao.search(type, data);
	}

	@Override
	public List<Film> allFilm() {
		return dao.allFilm();
	}

	@Override
	public List<Director> allDirector() {
		return dao.allDirector();
	}

	@Override
	public List<MovieSearch> topIMDB() {
		return dao.topIMDB();
	}

	@Override
	public List<MovieSearch> topNew() {
		return dao.topNew();
	}

	@Override
	public Film getFilmById(Integer movieId) {
		return dao.getFilmById(movieId);
	}

	@Override
	public List<Film> getByCategory(String cate) {
		return dao.getByCategory(cate);
	}

}
