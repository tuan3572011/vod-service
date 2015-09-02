package com.vod.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vod.model.Episodes;
import com.vod.service.EpisodeService;

@RestController
public class EpisodeController {

	@Autowired
	private EpisodeService epService;

	@RequestMapping(value = "/episode/get/{epId}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<Episodes> doGet(@PathVariable("epId") Integer param) {
		System.out.println("da vo");
		Episodes episode = null;
		try {
			episode = epService.getById(param);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Episodes>(HttpStatus.NOT_FOUND);
		}

		if (episode != null) {
			System.out.println("NOT NULL" + episode.toString());
			return new ResponseEntity<Episodes>(episode, HttpStatus.OK);
		} else {
			System.out.println("NULL");
			return new ResponseEntity<Episodes>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/episode/getBySerie/{serieId}/{noEpisode}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<Episodes> doGetBySerieId(
			@PathVariable("serieId") Integer serieId,
			@PathVariable("noEpisode") Integer noEpisode) {
		Episodes ep = null;
		try {
			ep = epService.getEpisodeBySerie(serieId, noEpisode);
		} catch (Exception e) {
			return new ResponseEntity<Episodes>(HttpStatus.NOT_FOUND);
		}
		if (ep != null) {
			return new ResponseEntity<Episodes>(ep, HttpStatus.OK);
		} else {
			return new ResponseEntity<Episodes>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/episode/getListBySerie/{serieId}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<Episodes>> doGetBySerie(
			@PathVariable("serieId") Integer serieId) {
		List<Episodes> eps = null;
		try {
			eps = epService.getListBySerie(serieId);
		} catch (Exception e) {
			return new ResponseEntity<List<Episodes>>(HttpStatus.NOT_FOUND);
		}
		if (eps != null) {
			return new ResponseEntity<List<Episodes>>(eps, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Episodes>>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/episode/all", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<Episodes>> doGetAll() {
		List<Episodes> episodes = null;
		try {
			episodes = epService.getAll();
		} catch (Exception e) {
			return new ResponseEntity<List<Episodes>>(HttpStatus.NOT_FOUND);
		}

		if (episodes != null) {
			for (Episodes e : episodes) {
				e.getMovie_key();
			}
			return new ResponseEntity<List<Episodes>>(episodes, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Episodes>>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/episode/add", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseEntity<Void> doSave(@Valid @RequestBody Episodes episode) {
		if (epService.add(episode)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/episode/update", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseEntity<Void> doUpdate(@Valid @RequestBody Episodes episode) {
		if (epService.update(episode)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

}
