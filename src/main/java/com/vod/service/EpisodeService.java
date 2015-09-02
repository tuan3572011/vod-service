package com.vod.service;

import java.util.List;

import com.vod.model.Episodes;

public interface EpisodeService {
	public boolean add(Episodes episode);
	public boolean update(Episodes episode);
	public boolean remove(Episodes episode);
	public Episodes getById(Integer id);
	public List<Episodes> getAll();
	public List<Episodes> getListBySerie(Integer serieId);
	public Episodes getEpisodeBySerie(Integer serieId, Integer noEpisode);
}
