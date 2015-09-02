package com.vod.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vod.dao.CountryDao;
import com.vod.model.Country;
import com.vod.service.CountryService;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {
	@Autowired
	private CountryDao dao;

	@Override
	public boolean add(Country country) {
		return dao.add(country);
	}

	@Override
	public Country get(Integer id) {
		return dao.get(id);
	}

	@Override
	public List<Country> getAll() {
		return dao.getAll();
	}

	@Override
	public boolean deleleAll() {
		return dao.deleleAll();
	}

	@Override
	public Country getByName(String name) {
		return dao.getByName(name);
	}

}
