package com.vod.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.vod.model.MovieSearch;
import com.vod.model.MovieSerie;
import com.vod.service.CategoryService;
import com.vod.service.CountryService;
import com.vod.service.MovieSerieService;

@RestController
public class MovieSerieController {
	@Autowired
	private MovieSerieService serieService;
	@Autowired
	private CategoryService cateService;
	@Autowired
	private CountryService countryService;

	/***
	 * 
	 * @param param
	 * @param model
	 * @return getById MovieSerie
	 */
	@RequestMapping(value = "/serie/get/{serieId}", method = RequestMethod.GET)
	public ResponseEntity<MovieSerie> getMovieSerie(
			@PathVariable("serieId") int param) {
		MovieSerie serie = null;
		try {
			serie = serieService.getById(param);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<MovieSerie>(HttpStatus.NOT_FOUND);
		}
		if (serie != null) {
			return new ResponseEntity<MovieSerie>(serie, HttpStatus.OK);
		} else {
			return new ResponseEntity<MovieSerie>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * 
	 * @param model
	 * @return getAll MovieSerie
	 */
	@RequestMapping(value = "/serie/all", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<MovieSearch>> getAllMovieSerie() {
		List<MovieSerie> series = null;
		List<MovieSearch> mvs = new ArrayList<MovieSearch>();
		try {
			series = serieService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<MovieSearch>>(HttpStatus.NOT_FOUND);
		}
		if (series != null) {
			for (MovieSerie serie : series) {
				MovieSearch moS = new MovieSearch();
				moS.setId(serie.getId());
				moS.setType(serie.getType());
				moS.setEngName(serie.getEngName());
				moS.setName(serie.getName());
				moS.setVnName(serie.getVnName());
				moS.setImage(serie.getImage());
				moS.setPublishedYear(serie.getPublishedYear());
				moS.setImdb(serie.getImdb());
				moS.setTrailer(serie.getTrailer());
				mvs.add(moS);
			}
			return new ResponseEntity<List<MovieSearch>>(mvs, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<MovieSearch>>(HttpStatus.NOT_FOUND);
		}
	}

	/***
	 * 
	 * @param movie
	 * @param result
	 * @return add new movieSerie
	 */
	@RequestMapping(value = "/serie/add", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseEntity<Void> addSerie(@Valid @RequestBody MovieSerie movie) {
		if (serieService.add(movie)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	/***
	 * 
	 * @param param
	 * @return delete MovieSerie
	 */
	@RequestMapping(value = "/serie/delete/{serieId}", method = RequestMethod.DELETE)
	public @ResponseBody
	ResponseEntity<Void> removeSerie(@PathVariable(value = "serieId") int param) {
		MovieSerie serie = null;
		try {
			serie = serieService.getById(param);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		if (serie != null) {
			if (serieService.remove(serie)) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/serie/update", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseEntity<Void> updateSerie(@Valid @RequestBody MovieSerie movie) {

		if (serieService.update(movie)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/serie/getByYear/{year}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<MovieSearch>> getByYear(
			@PathVariable("year") Integer year) {
		List<MovieSerie> series = null;
		List<MovieSearch> mvs = new ArrayList<MovieSearch>();
		try {
			series = serieService.getByYear(year);
		} catch (Exception e) {
			return new ResponseEntity<List<MovieSearch>>(HttpStatus.NOT_FOUND);
		}
		if (series != null) {
			for (MovieSerie serie : series) {
				MovieSearch moS = new MovieSearch();
				moS.setId(serie.getId());
				moS.setType(serie.getType());
				moS.setEngName(serie.getEngName());
				moS.setName(serie.getName());
				moS.setVnName(serie.getVnName());
				moS.setImage(serie.getImage());
				moS.setPublishedYear(serie.getPublishedYear());
				moS.setImdb(serie.getImdb());
				moS.setTrailer(serie.getTrailer());
				mvs.add(moS);
			}
			return new ResponseEntity<List<MovieSearch>>(mvs, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<MovieSearch>>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/serie/getByCategory/{cateId}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<MovieSearch>> doGetByCategory(
			@PathVariable("cateId") Integer cateId) {
		List<MovieSearch> mvs = new ArrayList<MovieSearch>();
		Category category = cateService.get(cateId);
		Set<Film> films = category.getFilms();
		for (Film f : films) {
			if (f.getType().equalsIgnoreCase("serie")) {
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
		}
		return new ResponseEntity<List<MovieSearch>>(mvs, HttpStatus.OK);
	}

	@RequestMapping(value = "/serie/getByCountry/{countryId}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<MovieSearch>> doGetByCountry(
			@PathVariable("countryId") Integer countryId) {
		List<MovieSearch> mvs = new ArrayList<MovieSearch>();
		Country country = countryService.get(countryId);
		Set<Film> films = country.getFilms();
		for (Film f : films) {
			if (f.getType().equalsIgnoreCase("serie")) {
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
		}
		return new ResponseEntity<List<MovieSearch>>(mvs, HttpStatus.OK);
	}

	@RequestMapping(value = "/serie/getByRate", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<MovieSearch>> doGetByRate() {
		List<MovieSerie> series = null;
		List<MovieSearch> mvs = new ArrayList<MovieSearch>();
		try {
			series = serieService.getByRate();
		} catch (Exception e) {
			return new ResponseEntity<List<MovieSearch>>(HttpStatus.NOT_FOUND);
		}
		if (series != null) {
			for (MovieSerie serie : series) {
				MovieSearch moS = new MovieSearch();
				moS.setId(serie.getId());
				moS.setType(serie.getType());
				moS.setEngName(serie.getEngName());
				moS.setName(serie.getName());
				moS.setVnName(serie.getVnName());
				moS.setImage(serie.getImage());
				moS.setPublishedYear(serie.getPublishedYear());
				moS.setImdb(serie.getImdb());
				moS.setTrailer(serie.getTrailer());
				mvs.add(moS);
			}
			return new ResponseEntity<List<MovieSearch>>(mvs, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<MovieSearch>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/serie/getByView", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<MovieSearch>> doGetByView() {
		List<MovieSerie> series = null;
		List<MovieSearch> mvs = new ArrayList<MovieSearch>();
		try {
			series = serieService.getByView();
		} catch (Exception e) {
			return new ResponseEntity<List<MovieSearch>>(HttpStatus.NOT_FOUND);
		}
		if (series != null) {
			for (MovieSerie serie : series) {
				MovieSearch moS = new MovieSearch();
				moS.setId(serie.getId());
				moS.setType(serie.getType());
				moS.setEngName(serie.getEngName());
				moS.setName(serie.getName());
				moS.setVnName(serie.getVnName());
				moS.setImage(serie.getImage());
				moS.setPublishedYear(serie.getPublishedYear());
				moS.setImdb(serie.getImdb());
				moS.setTrailer(serie.getTrailer());
				mvs.add(moS);
			}
			return new ResponseEntity<List<MovieSearch>>(mvs, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<MovieSearch>>(HttpStatus.NOT_FOUND);
		}
	}

}