package com.vod.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vod.dao.RegisMovieDao;
import com.vod.model.RegisterFilm;
import com.vod.service.RegisMovieService;

@Service
@Transactional
public class RegisMovieServiceImpl implements RegisMovieService {

	@Autowired
	private RegisMovieDao dao;

	@Override
	public boolean add(RegisterFilm regis) {
		return dao.add(regis);
	}

	@Override
	public RegisterFilm getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public RegisterFilm getByUser(String email) {
		return dao.getByUser(email);
	}

	@Override
	public List<RegisterFilm> getListByUser(String email) {
		return dao.getListByUser(email);
	}

}
