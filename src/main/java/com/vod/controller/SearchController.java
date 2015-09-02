package com.vod.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vod.model.Category;
import com.vod.model.Film;
import com.vod.model.MovieSearch;
import com.vod.service.SearchService;

@RestController
public class SearchController {

	@Autowired
	private SearchService searchService;

	@RequestMapping(value = "/search/{searchData}/{searchType}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<MovieSearch>> doSearch(
			@PathVariable("searchData") String searchData,
			@PathVariable("searchType") String searchType) {
		List<MovieSearch> searchs = searchService
				.search(searchType, searchData);
		if (searchs != null) {
			return new ResponseEntity<List<MovieSearch>>(searchs, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<MovieSearch>>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/search/all/film", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ResponseEntity<List<MovieSearch>> doAllFilm() {
		List<Film> films = searchService.allFilm();
		List<MovieSearch> searchs = new ArrayList<MovieSearch>();
		for (Film f : films) {
			if (f.getType().equals("movie") || f.getType().equals("serie")) {
				MovieSearch m = new MovieSearch();
				m.setType(f.getType());
				m.setId(f.getId());
				m.setImage(f.getImage());
				m.setEngName(f.getEngName());
				m.setVnName(f.getVnName());
				m.setTrailer(f.getTrailer());
				searchs.add(m);
			}
		}
		return new ResponseEntity<List<MovieSearch>>(searchs, HttpStatus.OK);

	}

	@RequestMapping(value = "/film/topIMDB", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<MovieSearch>> doGetTopIMDB() {
		List<MovieSearch> searchs = null;
		try {
			searchs = searchService.topIMDB();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<MovieSearch>>(HttpStatus.NOT_FOUND);
		}
		if (searchs != null) {
			return new ResponseEntity<List<MovieSearch>>(searchs, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<MovieSearch>>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/film/topNew", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<MovieSearch>> doGetTopNew() {
		List<MovieSearch> searchs = null;
		try {
			searchs = searchService.topNew();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<MovieSearch>>(HttpStatus.NOT_FOUND);
		}
		if (searchs != null) {
			return new ResponseEntity<List<MovieSearch>>(searchs, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<MovieSearch>>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/film/relatedMovies/{filmId}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<MovieSearch>> doGetFilmById(
			@PathVariable("filmId") int movieId) {
		Film film = null;
		List<MovieSearch> searchs = new ArrayList<MovieSearch>();
		try {
			film = searchService.getFilmById(movieId);
		} catch (Exception e) {
			return new ResponseEntity<List<MovieSearch>>(HttpStatus.BAD_REQUEST);
		}
		Set<Film> f = new HashSet<Film>();
		if (film != null) {
			Set<Category> cates = film.getCategories();
			for (Category c : cates) {
				f.addAll(c.getFilms());
			}
		}
		int sum = 0;
		Iterator<Film> iterator = f.iterator();
		while (iterator.hasNext()) {
			Film fss = iterator.next();
			if (fss.getType().equals(film.getType())) {
				sum++;
				MovieSearch m = new MovieSearch();
				m.setType(fss.getType());
				m.setId(fss.getId());
				m.setImage(fss.getImage());
				m.setEngName(fss.getEngName());
				m.setVnName(fss.getVnName());
				m.setTrailer(fss.getTrailer());
				if (sum < 20 && !fss.getId().equals(film.getId())) {
					searchs.add(m);
				} else {
					break;
				}
			}
		}
		return new ResponseEntity<List<MovieSearch>>(searchs, HttpStatus.OK);
	}

	@RequestMapping(value = "/film/all", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	List<Film> doGetListFilm() {
		List<Film> fs = new ArrayList<Film>();
		List<Film> films = searchService.allFilm();
		for (Film f : films) {
			if (f.getType().equalsIgnoreCase("movie")
					|| f.getType().equalsIgnoreCase("serie")) {
				fs.add(f);
			}
		}
		return fs;
	}

}
