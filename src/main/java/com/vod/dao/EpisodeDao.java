package com.vod.dao;

import java.util.List;

import com.vod.model.Episodes;

public interface EpisodeDao {
	public boolean add(Episodes episode);
	public boolean update(Episodes episode);
	public boolean remove(Episodes episode);
	public Episodes getById(Integer id);
	public List<Episodes> getAll();
	public List<Episodes> getListBySerie(Integer serieId);
	public Episodes getEpisodeBySerie(Integer serieId, Integer noEpisode);
}
