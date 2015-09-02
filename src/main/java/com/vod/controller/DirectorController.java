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

import com.vod.model.Director;
import com.vod.service.DirectorService;

@RestController
public class DirectorController {
	@Autowired
	private DirectorService directorService;

	@RequestMapping(value = "/director/add", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseEntity<Void> doSave(@Valid @RequestBody Director director) {
		if (directorService.add(director)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/director/update", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseEntity<Void> doUpdate(@Valid @RequestBody Director director) {
		if (directorService.update(director)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/director/get/{directorId}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<Director> doGet(@PathVariable("cateId") int param) {
		Director director = null;
		try {
			director = directorService.get(param);
		} catch (Exception e) {
			return new ResponseEntity<Director>(HttpStatus.NOT_FOUND);
		}

		if (director != null) {
			return new ResponseEntity<Director>(director, HttpStatus.OK);
		} else {
			return new ResponseEntity<Director>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/director/all", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<Director>> doGetAll() {
		List<Director> directors = null;
		try {
			directors = directorService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Director>>(HttpStatus.NOT_FOUND);
		}

		if (directors != null) {
			return new ResponseEntity<List<Director>>(directors, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Director>>(HttpStatus.NOT_FOUND);
		}
	}

}
