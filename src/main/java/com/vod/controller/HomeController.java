package com.vod.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vod.model.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	static final Logger logger = Logger.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		Date date = new Date();
		//
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		///
		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/user/get", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	User get() {
		User user = new User();
		user.setUserName("ahah");

		return user;
	}

}