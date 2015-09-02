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

import com.vod.model.Starring;
import com.vod.service.StarringService;

@RestController
public class StarringController {
	@Autowired
	private StarringService starService;

	@RequestMapping(value = "/starring/get/{starId}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<Starring> doGet(@PathVariable("starId") int param) {
		Starring star = null;
		try {
			star = starService.get(param);
		} catch (Exception e) {
			return new ResponseEntity<Starring>(HttpStatus.NOT_FOUND);
		}
		if (star != null) {
			return new ResponseEntity<Starring>(star, HttpStatus.OK);

		} else {
			return new ResponseEntity<Starring>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/starring/add", method = RequestMethod.POST)
	public @ResponseBody
	ResponseEntity<Void> doSave(@Valid @RequestBody Starring star) {

		if (starService.add(star)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/starring/update", method = RequestMethod.POST)
	public @ResponseBody
	ResponseEntity<Void> doUpdate(@Valid @RequestBody Starring star) {

		if (starService.update(star)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/starring/all", produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<Starring>> doGetAll() {
		List<Starring> stars = null;
		try {
			stars = starService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Starring>>(HttpStatus.BAD_REQUEST);
		}
		if (stars != null) {
			return new ResponseEntity<List<Starring>>(stars, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Starring>>(HttpStatus.BAD_REQUEST);
		}

	}
}
