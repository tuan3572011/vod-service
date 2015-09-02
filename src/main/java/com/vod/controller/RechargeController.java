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

import com.vod.model.Card;
import com.vod.model.RechargeCard;
import com.vod.model.User;
import com.vod.service.CardService;
import com.vod.service.RechargeService;
import com.vod.service.UserService;

@RestController
public class RechargeController {
	@Autowired
	private RechargeService service;
	@Autowired
	private CardService cardService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/recharge/add/{email}/{seri}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<String> doAdd(@PathVariable("email") String email,
			@PathVariable("seri") String seri) {
		String message = "";
		Card card = getCardBySeri(seri);
		User user = getUserByEmail(email);
		if (card != null) {
			RechargeCard recharge = new RechargeCard();
			recharge.setUser(user);
			recharge.setValue(card.getValue());
			if (service.add(recharge)) {
				double amount = user.getAccountAmount() + card.getValue();
				user.setAccountAmount(amount);
				card.setIsUsed(true);
				if (updateCard(card) && updateUser(user)) {
					message = "You have loaded into your account " + card.getValue()
							+ "vnd";
				} else {
					message = "Transaction Errors.";
				}
			} else {
				message = "Transaction Errors.";
			}
		} else {
			message = "Card has used or not right.";
		}
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	private Card getCardBySeri(String seri) {
		Card card = null;
		try {
			card = cardService.getBySeri(seri);
		} catch (Exception e) {
			return null;
		}
		return card;
	}

	private boolean updateCard(Card card) {
		try {
			cardService.update(card);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private User getUserByEmail(String email) {
		User user = null;
		try {
			user = userService.getByEmail(email);
		} catch (Exception e) {
			return null;
		}

		return user;
	}

	private boolean updateUser(User user) {
		try {
			userService.update(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@RequestMapping(value = "/recharge/update", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody
	ResponseEntity<Void> doUpdate(@RequestBody RechargeCard card) {
		if (service.update(card)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/recharge/getById/{rechargeId}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<RechargeCard> doGetById(
			@PathVariable("rechargeId") int rechargeId) {
		RechargeCard card = null;
		try {
			card = service.getById(rechargeId);
		} catch (Exception e) {
			return new ResponseEntity<RechargeCard>(HttpStatus.NOT_FOUND);
		}
		if (card != null) {
			return new ResponseEntity<RechargeCard>(card, HttpStatus.OK);
		} else {
			return new ResponseEntity<RechargeCard>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/recharge/getByUser/{email}/user", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<RechargeCard>> doGetByUser(
			@PathVariable("email") String email) {
		List<RechargeCard> cards = null;
		try {
			cards = service.getByUser(email);
		} catch (Exception e) {
			return new ResponseEntity<List<RechargeCard>>(HttpStatus.NOT_FOUND);
		}
		if (cards != null) {
			return new ResponseEntity<List<RechargeCard>>(cards, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<RechargeCard>>(HttpStatus.NOT_FOUND);
		}
	}
}
