package com.vod.dao;

import java.util.List;

import com.vod.model.Director;

public interface DirectorDao {
	public boolean add(Director director);
	public boolean deleteAll();
	public boolean update(Director director);
	public Director get(Integer id);
	public List<Director> getAll();
}
