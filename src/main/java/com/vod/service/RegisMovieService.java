package com.vod.service;

import java.util.List;

import com.vod.model.RegisterFilm;

public interface RegisMovieService {
	public boolean add(RegisterFilm regis);
	public RegisterFilm getById(Integer id);
	public RegisterFilm getByUser(String email);
	public List<RegisterFilm> getListByUser(String email);
}
