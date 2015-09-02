package com.vod.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.vod.model.Episodes;
import com.vod.model.RegisterFilm;
import com.vod.service.CommentService;
import com.vod.service.EpisodeService;
import com.vod.service.MovieSerieService;
import com.vod.service.MovieService;
import com.vod.service.RegisMovieService;
import com.vod.service.UserService;

@Controller
public class TestController {

	@Autowired
	private CommentService cService;
	@Autowired
	private MovieService mService;
	@Autowired
	private MovieSerieService serieService;
	@Autowired
	private UserService uService;
	@Autowired
	private EpisodeService epService;
	@Autowired
	private RegisMovieService regisService;

	@RequestMapping(value = "/test/regis", method = RequestMethod.GET)
	public @ResponseBody
	String doAddComment() {
		String message = "";

		// List<RegisterFilm> regis = doGetRegisByUser("admin@gmail.com");
		// System.out.println(regis.size());
		// for (RegisterFilm r : regis) {
		// System.out.println(r.getFilm().getId());
		// }

		// Movie movie = mService.get(17);
		// Episodes movie = epService.getById(19);
		// MovieSerie movie = serieService.getById(3);
		// User user = uService.getByEmail("admin@gmail.com");

		// RegisterFilm regis = new RegisterFilm();
		// regis.setFilm(movie);
		// regis.setUser(user);
		// regis.setNoDateExpires(3);
		// Comment comment = new Comment();
		// comment.setContent("hahahaha");
		// comment.setMovieOdd(movie);
		// comment.setUser(user);

		// if (regisService.add(regis)) {
		// System.out.println("OK");
		// } else {
		// System.out.println("FAIL");
		// }

		// final String url = "http://localhost:8080/vodService/comment/add";
		// RestTemplate restTemplate = new RestTemplate();
		// ResponseEntity<Void> entity = restTemplate.postForEntity(url,
		// comment,
		// Void.class);
		// if (entity.getStatusCode().equals(HttpStatus.OK)) {
		// System.out.println("OK");
		// } else {
		// System.out.println("FAIL");
		// }
		String email = "admin@gmail.com";
		String idFilm = "19";
		String type = "episode";

		final String url = "http://localhost:8080/vodService/regis/addregis/{email}/{idFilm}/{type}";
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		params.put("email", email);
		params.put("idFilm", idFilm);
		params.put("type", type);

		try {
			restTemplate.getForObject(url, String.class, params);
			message = "OK";
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
				message = "FAIL";
			}
		}

		// ResponseEntity<Void> entity = restTemplate.postForEntity(url, null,
		// null);
		// if (entity.getStatusCode().equals(HttpStatus.OK)) {
		// message = "OK";
		// } else {
		// message = "FAIL";
		// }

		// final String url = "http://localhost:8080/vodService/regis/add";
		// RestTemplate restTemplate = new RestTemplate();
		// ResponseEntity<Void> entity = restTemplate.postForEntity(url, regis,
		// Void.class);
		// if (entity.getStatusCode().equals(HttpStatus.OK)) {
		// System.out.println("OK");
		// } else {
		// System.out.println("FAIL");
		// }

		return message;
	}

	@RequestMapping(value = "/test/recharge/{email}/{seri}", method = RequestMethod.GET)
	public @ResponseBody
	String doTestRecharge(@PathVariable("email") String email,
			@PathVariable("seri") String seri) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		params.put("seri", seri);
		params.put("email", email);
		String result = restTemplate.getForObject(
				"http://localhost:8080/vodService/recharge/add/{email}/{seri}",
				String.class, params);
		System.out.println(result);
		return result;
	}

	@RequestMapping(value = "/test/ep")
	public List<Episodes> doGetAll() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/vodService/episode/all";
		String str = restTemplate.getForObject(url, String.class);
		System.out.println(str);
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Episodes> list = mapper.readValue(
					str,
					TypeFactory.defaultInstance().constructCollectionType(
							List.class, Episodes.class));
			for (Episodes e : list) {
				System.out.println(e.getUpdatedDate());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unused")
	private List<RegisterFilm> doGetRegisByUser(String email) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("email", email);
		RestTemplate restTemplate = new RestTemplate();

		String str = restTemplate
				.getForObject(
						"http://localhost:8080/vodService/regis/getListByUser/{email}/user",
						String.class, params);
		ObjectMapper mapper = new ObjectMapper();
		List<RegisterFilm> regis = new ArrayList<RegisterFilm>();
		try {
			regis = mapper.readValue(str, TypeFactory.defaultInstance()
					.constructCollectionType(List.class, Episodes.class));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return regis;
	}
}
