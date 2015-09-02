package com.vod.dao;

import java.util.List;

import com.vod.model.Starring;

public interface StarringDao {
	public boolean add(Starring starring);
	public boolean deleteAll();
	public boolean update(Starring starring);
	public Starring get(Integer id);
	public List<Starring> getAll();
}
