package com.vod.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vod.dao.DirectorDao;
import com.vod.model.Director;
import com.vod.service.DirectorService;

@Service
@Transactional
public class DirectorServiceImpl implements DirectorService {
	@Autowired
	private DirectorDao dao;

	@Override
	public boolean add(Director director) {
		return dao.add(director);
	}

	@Override
	public boolean update(Director director) {
		return dao.update(director);
	}

	@Override
	public Director get(Integer id) {
		return dao.get(id);
	}

	@Override
	public List<Director> getAll() {
		return dao.getAll();
	}

	@Override
	public boolean deleteAll() {
		return dao.deleteAll();
	}

}
