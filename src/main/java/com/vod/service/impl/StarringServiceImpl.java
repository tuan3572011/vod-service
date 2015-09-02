package com.vod.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vod.dao.StarringDao;
import com.vod.model.Starring;
import com.vod.service.StarringService;

@Service
@Transactional
public class StarringServiceImpl implements StarringService {
	@Autowired
	private StarringDao dao;

	@Override
	public boolean add(Starring starring) {
		return dao.add(starring);
	}

	@Override
	public boolean update(Starring starring) {
		return dao.update(starring);
	}

	@Override
	public Starring get(Integer id) {
		return dao.get(id);
	}

	@Override
	public List<Starring> getAll() {
		return dao.getAll();
	}

	@Override
	public boolean deleteAll() {
		return dao.deleteAll();
	}

}
