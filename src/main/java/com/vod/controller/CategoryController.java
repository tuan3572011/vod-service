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

import com.vod.model.Category;
import com.vod.service.CategoryService;

@RestController
public class CategoryController {
	@Autowired
	private CategoryService cateService;

	@RequestMapping(value = "/category/get/{cateId}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<Category> doGet(@PathVariable("cateId") int param) {
		Category cate = null;
		try {
			cate = cateService.get(param);
		} catch (Exception e) {
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		}

		if (cate != null) {
			return new ResponseEntity<Category>(cate, HttpStatus.OK);
		} else {
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/category/add", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseEntity<Void> doSave(@Valid @RequestBody Category category) {
		if (cateService.add(category)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/category/update", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseEntity<Void> doUpdate(@Valid @RequestBody Category category) {
		if (cateService.update(category)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/category/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ResponseEntity<List<Category>> doGetAll() {
		List<Category> cates = null;
		try {
			cates = cateService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Category>>(HttpStatus.NOT_FOUND);
		}

		if (cates != null) {
			return new ResponseEntity<List<Category>>(cates, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Category>>(HttpStatus.NOT_FOUND);
		}
	}

}
