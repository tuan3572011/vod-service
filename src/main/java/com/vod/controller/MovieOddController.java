package com.vod.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vod.model.MovieOdd;
import com.vod.service.MovieOddService;

@RestController
public class MovieOddController {
	@Autowired
	private MovieOddService oddService;

	@RequestMapping(value = "/movieodd/all", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<MovieOdd>> getAll() {
		List<MovieOdd> odds = null;
		try {
			odds = oddService.getAll();
		} catch (Exception e) {
			return new ResponseEntity<List<MovieOdd>>(HttpStatus.NOT_FOUND);
		}

		if (odds != null) {
			return new ResponseEntity<List<MovieOdd>>(odds, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<MovieOdd>>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/movieodd/getByView", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<MovieOdd>> getByView() {
		List<MovieOdd> odds = null;
		try {
			odds = oddService.getByView();
		} catch (Exception e) {
			return new ResponseEntity<List<MovieOdd>>(HttpStatus.NOT_FOUND);
		}

		if (odds != null) {
			return new ResponseEntity<List<MovieOdd>>(odds, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<MovieOdd>>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/movieodd/getByRate", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<MovieOdd>> getByRate() {
		List<MovieOdd> odds = null;
		try {
			odds = oddService.getByRate();
		} catch (Exception e) {
			return new ResponseEntity<List<MovieOdd>>(HttpStatus.NOT_FOUND);
		}

		if (odds != null) {
			return new ResponseEntity<List<MovieOdd>>(odds, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<MovieOdd>>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/movieodd/get/{movieId}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<MovieOdd> doGetById(@PathVariable("movieId") int movieId) {
		MovieOdd odd = null;
		try {
			odd = oddService.get(movieId);
		} catch (Exception e) {
			return new ResponseEntity<MovieOdd>(HttpStatus.NOT_FOUND);
		}

		if (odd != null) {
			return new ResponseEntity<MovieOdd>(odd, HttpStatus.OK);
		} else {
			return new ResponseEntity<MovieOdd>(HttpStatus.NOT_FOUND);
		}
	}

}
