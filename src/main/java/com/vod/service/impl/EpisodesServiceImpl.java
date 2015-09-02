package com.vod.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vod.dao.EpisodeDao;
import com.vod.model.Episodes;
import com.vod.service.EpisodeService;

@Service
@Transactional
public class EpisodesServiceImpl implements EpisodeService {
	@Autowired
	private EpisodeDao dao;

	@Override
	public boolean add(Episodes episode) {
		return dao.add(episode);
	}

	@Override
	public boolean update(Episodes episode) {
		return dao.update(episode);
	}

	@Override
	public boolean remove(Episodes episode) {
		return dao.remove(episode);
	}

	@Override
	public Episodes getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public List<Episodes> getAll() {
		return dao.getAll();
	}

	@Override
	public Episodes getEpisodeBySerie(Integer serieId, Integer noEpisode) {
		return dao.getEpisodeBySerie(serieId, noEpisode);
	}

	@Override
	public List<Episodes> getListBySerie(Integer serieId) {
		return dao.getListBySerie(serieId);
	}

}
