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

import com.vod.model.Country;
import com.vod.service.CountryService;

@RestController
public class CountryController {
	@Autowired
	private CountryService cService;

	@RequestMapping(value = "/country/add", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseEntity<Void> doAdd(@Valid @RequestBody Country country) {
		if (cService.add(country)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/country/get/{countryId}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<Country> doGetCountry(@PathVariable("countryId") int param) {
		Country country = null;
		try {
			country = cService.get(param);
		} catch (Exception e) {
			return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
		}
		if (country != null) {
			return new ResponseEntity<Country>(country, HttpStatus.OK);
		} else {
			return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/country/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ResponseEntity<List<Country>> doGetAll() {
		List<Country> countries = null;
		try {
			countries = cService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Country>>(HttpStatus.NOT_FOUND);
		}
		if (countries != null) {
			return new ResponseEntity<List<Country>>(countries, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Country>>(HttpStatus.NOT_FOUND);

		}
	}

}
