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

import com.vod.model.Comment;
import com.vod.model.Episodes;
import com.vod.model.Movie;
import com.vod.service.CommentService;
import com.vod.service.EpisodeService;
import com.vod.service.MovieService;

@RestController
public class CommentController {
	@Autowired
	private CommentService cService;
	@Autowired
	private MovieService mService;
	@Autowired
	private EpisodeService epService;

	@RequestMapping(value = "/comment/add", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody
	ResponseEntity<Void> doAdd(@Valid @RequestBody Comment comment) {
		if (cService.add(comment)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/comment/add/{movieOddId}/{type}", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody
	ResponseEntity<Void> doAddComment(@Valid @RequestBody Comment comment,
			@PathVariable("movieOddId") Integer movieOddId,
			@PathVariable("type") String type) {
		if (type.equalsIgnoreCase("movie")) {
			Movie movie = mService.get(movieOddId);
			comment.setMovieOdd(movie);
		} else {
			Episodes episode = epService.getById(movieOddId);
			comment.setMovieOdd(episode);
		}
		if (cService.add(comment)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/comment/getById/{commentId}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<Comment> doGetById(@PathVariable("commentId") int commentId) {
		Comment comment = null;
		try {
			comment = cService.getById(commentId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
		}

		if (comment != null) {
			return new ResponseEntity<Comment>(comment, HttpStatus.OK);
		} else {
			return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/comment/getByUser/{email}/user", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<Comment>> doGetByUser(
			@PathVariable("email") String email) {
		List<Comment> comments = null;
		try {
			comments = cService.getByUser(email);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Comment>>(HttpStatus.NOT_FOUND);
		}

		if (comments != null) {
			return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Comment>>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/comment/getByFilm/{filmId}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<Comment>> doGetByFilm(@PathVariable("filmId") int filmId) {
		List<Comment> comments = null;
		try {
			comments = cService.getByFilm(filmId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Comment>>(HttpStatus.NOT_FOUND);
		}

		if (comments != null) {
			return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Comment>>(HttpStatus.NOT_FOUND);
		}
	}

}
