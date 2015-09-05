package com.vod.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

import com.vod.model.Category;
import com.vod.model.Country;
import com.vod.model.Film;
import com.vod.model.Movie;
import com.vod.model.MovieSearch;
import com.vod.service.CategoryService;
import com.vod.service.CountryService;
import com.vod.service.MovieService;

@RestController
public class MovieController {
	@Autowired
	private MovieService mService;
	@Autowired
	private CategoryService cateService;
	@Autowired
	private CountryService countryService;

	@RequestMapping(value = "/movie/get/{movieId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Movie> doGet(@PathVariable("movieId") int param) {
		Movie movie = null;
		try {
			movie = mService.get(param);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
		}

		if (movie != null) {
			return new ResponseEntity<Movie>(movie, HttpStatus.OK);
		} else {
			return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/movie/all", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<MovieSearch>> doGetAll() {
		List<Movie> movies = null;
		List<MovieSearch> mvs = new ArrayList<MovieSearch>();
		try {
			movies = mService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<MovieSearch>>(HttpStatus.NOT_FOUND);
		}

		if (movies != null) {
			for (Movie f : movies) {
				MovieSearch moS = new MovieSearch();
				moS.setId(f.getId());
				moS.setType(f.getType());
				moS.setEngName(f.getEngName());
				moS.setName(f.getName());
				moS.setVnName(f.getVnName());
				moS.setImage(f.getImage());
				moS.setPublishedYear(f.getPublishedYear());
				moS.setImdb(f.getImdb());
				moS.setTrailer(f.getTrailer());
				mvs.add(moS);
			}
			return new ResponseEntity<List<MovieSearch>>(mvs, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<MovieSearch>>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/movie/add", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<Void> doSave(@Valid @RequestBody Movie movie) {
		if (mService.add(movie)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/movie/update", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<Void> doUpdate(@Valid @RequestBody Movie movie) {
		if (mService.update(movie)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/movie/getBy/{orderId}/{categoryId}/{publishYear}/{countryId}/{page}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<MovieSearch>> doGetByYear(@PathVariable("publishYear") Integer year,
			@PathVariable("orderId") Integer orderId, @PathVariable("categoryId") Integer categoryId,
			@PathVariable("countryId") Integer countryId, @PathVariable("page") Integer page) {
		List<MovieSearch> mvs = new ArrayList<MovieSearch>();
		List<Movie> movies = null;
		try {
			Category category = cateService.get(categoryId);
			Country country = countryService.get(countryId);
			movies = mService.filterBy(orderId, category, year, country, page);
		} catch (Exception e) {
			return new ResponseEntity<List<MovieSearch>>(HttpStatus.NOT_FOUND);
		}
		if (movies != null) {
			for (Movie f : movies) {
				MovieSearch moS = new MovieSearch();
				moS.setId(f.getId());
				moS.setType(f.getType());
				moS.setEngName(f.getEngName());
				moS.setName(f.getName());
				moS.setVnName(f.getVnName());
				moS.setImage(f.getImage());
				moS.setPublishedYear(f.getPublishedYear());
				moS.setImdb(f.getImdb());
				moS.setTrailer(f.getTrailer());
				mvs.add(moS);
			}
			return new ResponseEntity<List<MovieSearch>>(mvs, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<MovieSearch>>(HttpStatus.NOT_FOUND);
		}
	}

}
