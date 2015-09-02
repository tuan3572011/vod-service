package com.vod.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vod.model.User;
import com.vod.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user/get/{email}/login", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<User> doGetByEmail(@PathVariable("email") String email) {
		User user = null;
		try {
			user = userService.getByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/user/all", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<User>> doGetAll() {
		List<User> users = null;
		try {
			users = userService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
		}

		if (users != null) {
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody
	ResponseEntity<Void> doAdd(@RequestBody User user) {
		if (userService.add(user)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/user/update", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody
	ResponseEntity<Void> doUpdate(@RequestBody User user) {
		if (userService.update(user)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

}
