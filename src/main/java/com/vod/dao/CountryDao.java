package com.vod.dao;

import java.util.List;

import com.vod.model.Country;

public interface CountryDao {
	public boolean add(Country country);
	public boolean deleleAll();
	public Country get(Integer id);
	public Country getByName(String name);
	public List<Country> getAll();
}
