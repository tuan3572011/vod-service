package com.vod.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vod.model.Episodes;
import com.vod.model.Movie;
import com.vod.model.MovieSerie;
import com.vod.model.RegisInfor;
import com.vod.model.RegisterFilm;
import com.vod.model.User;
import com.vod.service.EpisodeService;
import com.vod.service.MovieSerieService;
import com.vod.service.MovieService;
import com.vod.service.RegisMovieService;
import com.vod.service.UserService;

@RestController
public class RegisController {
	@Autowired
	private RegisMovieService regisService;
	@Autowired
	private UserService userService;
	@Autowired
	private MovieService movieService;
	@Autowired
	private EpisodeService episodeService;
	@Autowired
	private MovieSerieService serieService;

	@RequestMapping(value = "/regis/add", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody
	ResponseEntity<Void> doAdd(@Valid @RequestBody RegisterFilm regis) {
		if (regisService.add(regis)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/regis/addregis/{email}/{idFilm}/{type}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<Void> doAdd(@PathVariable("email") String email,
			@PathVariable("idFilm") Integer idFilm,
			@PathVariable("type") String type) {

		User user = null;
		Movie movie = null;
		Episodes episode = null;
		MovieSerie serie = null;
		double userAmountNow = 0;
		System.out.println("Begin");
		try {
			user = userService.getByEmail(email);
			System.out.println(user.getAccountAmount());
			double userAmount = user.getAccountAmount();
			double price = 0;
			if (type.equalsIgnoreCase("movie")) {
				movie = movieService.get(idFilm);
				price = movie.getPrice();
				System.out.println("movie");
			} else if (type.equalsIgnoreCase("serie")) {
				serie = serieService.getById(idFilm);
				price = serie.getPrice();
				System.out.println("serie");
			} else {
				episode = episodeService.getById(idFilm);
				price = episode.getPrice();
				System.out.println("episode");
			}
			// Check if amount of user less than price return false
			if (!checkAmount(userAmount, price)) {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
			// Subtract money into amount user
			userAmountNow = userAmount - price;
			System.out.println(userAmountNow);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		RegisterFilm register = new RegisterFilm();
		boolean check = false;
		if (movie != null) {
			register.setFilm(movie);
			register.setNoDateExpires(2);
		} else if (serie != null) {
			register.setFilm(serie);
			register.setNoDateExpires(serie.getNumEpisodes() * 2);
		} else {
			register.setFilm(episode);
			register.setNoDateExpires(2);
		}

		// Register film
		if (user != null) {
			register.setUser(user);
			try {
				regisService.add(register);
				check = true;
				System.out.println("Add Regis OK");
			} catch (Exception e) {
				System.out.println("Add Regis Fail");
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
		}

		// If register success then update account with set new amount
		if (check) {
			user.setAccountAmount(userAmountNow);
			try {
				userService.update(user);
				System.out.println("Update User Ok");
			} catch (Exception e) {
				System.out.println("Update User fail");
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
		}

		// If success all return OK
		if (check) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/regis/getById/{regisId}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<RegisterFilm> doGetById(@PathVariable("regisId") int regisId) {
		RegisterFilm regis = null;
		try {
			regis = regisService.getById(regisId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<RegisterFilm>(HttpStatus.NOT_FOUND);
		}

		if (regis != null) {
			return new ResponseEntity<RegisterFilm>(regis, HttpStatus.OK);
		} else {
			return new ResponseEntity<RegisterFilm>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/regis/getByUser/{email}/user", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<RegisterFilm> doGetByUser(@PathVariable("email") String email) {
		RegisterFilm regis = null;
		try {
			regis = regisService.getByUser(email);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<RegisterFilm>(HttpStatus.NOT_FOUND);
		}

		if (regis != null) {
			return new ResponseEntity<RegisterFilm>(regis, HttpStatus.OK);
		} else {
			return new ResponseEntity<RegisterFilm>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/regis/getListByUser/{email}/user", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<RegisterFilm>> doGetListByUser(
			@PathVariable("email") String email) {
		List<RegisterFilm> regis = null;
		try {
			regis = regisService.getListByUser(email);
		} catch (Exception e) {
			return new ResponseEntity<List<RegisterFilm>>(HttpStatus.NOT_FOUND);
		}
		if (regis != null) {
			return new ResponseEntity<List<RegisterFilm>>(regis, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<RegisterFilm>>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/regis/{email}/infor", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<RegisInfor>> doGetInfor(
			@PathVariable("email") String email) {
		List<RegisterFilm> regis = null;
		List<RegisInfor> infors = new ArrayList<RegisInfor>();
		Date dateNow = new Date();
		try {
			regis = regisService.getListByUser(email);
		} catch (Exception e) {
			return new ResponseEntity<List<RegisInfor>>(HttpStatus.NOT_FOUND);
		}
		if (regis != null) {
			for (RegisterFilm f : regis) {
				int minutesLeft = minutesLeft(dateNow, f.getRegisterDate(),
						f.getNoDateExpires());
				System.out.println("XXXXXXX" + f.getId());
				if (minutesLeft > 0) {
					RegisInfor inf = new RegisInfor();
					System.out.println("XXXXXXXX "+f.getFilm().getEngName());
					inf.setMinutesLeft(minutesLeft + "");
					inf.setRegisDate(f.getRegisterDate() + "");
					
					if (f.getFilm().getType().equalsIgnoreCase("episode")) {
						System.out.println("OKOKOK");
						inf.setMovieName(movieName(f.getFilm().getId()));
					} else {
						inf.setMovieName(f.getFilm().getEngName());
					}
					infors.add(inf);
				}
			}
		}

		return new ResponseEntity<List<RegisInfor>>(infors, HttpStatus.OK);
	}

	@RequestMapping(value = "/regis/getRegis/{email}/{idFilm}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<Void> doGetByUserNoEp(@PathVariable("email") String email,
			@PathVariable("idFilm") int idFilm) {
		List<RegisterFilm> regis = null;
		boolean check = false;
		Date dateNow = new Date();
		try {
			regis = regisService.getListByUser(email);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		if (regis != null) {
			for (RegisterFilm f : regis) {
				if (f.getFilm().getId() == idFilm) {
					if (checkExpireDate(dateNow, f.getRegisterDate(),
							f.getNoDateExpires())) {
						check = true;
						break;
					}
				}
			}
			if (check) {
				System.out.println("OK");
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				System.out.println("FAIL");
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	private boolean checkAmount(double userAmount, double price) {
		return (userAmount < price) ? false : true;
	}

	private static boolean checkExpireDate(Date dateNow, Date dateRegis,
			int noDateUse) {
		DateTime dt1 = new DateTime(dateNow);
		DateTime dt2 = new DateTime(dateRegis);
		long sUse = noDateUse * 24 * 60 * 60;
		long stime = Math.abs(Seconds.secondsBetween(dt1, dt2).getSeconds());
		System.out.println(sUse + " ----- " + stime);
		return (stime >= sUse) ? false : true;
	}

	private int minutesLeft(Date dateNow, Date dateRegis, int noDateUse) {
		DateTime dt1 = new DateTime(dateNow);
		DateTime dt2 = new DateTime(dateRegis);
		long sUse = noDateUse * 24 * 60 * 60;
		long stime = Math.abs(Seconds.secondsBetween(dt1, dt2).getSeconds());

		return (int) ((sUse - stime) / 60);
	}

	private String movieName(Integer movieId) {
		Episodes ep = episodeService.getById(movieId);
		String movieName = ep.getMovieSerie().getVnName() + " [Tập "
				+ ep.getNoEpisode() + "]";
		return movieName;
	}

}
