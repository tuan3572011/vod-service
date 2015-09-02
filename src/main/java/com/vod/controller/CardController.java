package com.vod.controller;

import java.util.List;
import java.util.Random;

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

import com.vod.model.Card;
import com.vod.service.CardService;

@RestController
public class CardController {
	@Autowired
	private CardService service;

	@RequestMapping(value = "/card/all", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<Card>> doGetAll() {
		List<Card> cards = null;
		try {
			cards = service.getAll();
		} catch (Exception e) {
			return new ResponseEntity<List<Card>>(HttpStatus.NOT_FOUND);
		}
		if (cards != null) {
			return new ResponseEntity<List<Card>>(cards, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Card>>(HttpStatus.NOT_FOUND);
		}
	}

	/***
	 * 
	 * @return
	 */
	@RequestMapping(value = "/card/all/unused", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<Card>> doGetAllUnused() {
		List<Card> cards = null;
		try {
			cards = service.allUnused();
		} catch (Exception e) {
			return new ResponseEntity<List<Card>>(HttpStatus.NOT_FOUND);
		}
		if (cards != null) {
			return new ResponseEntity<List<Card>>(cards, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Card>>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/card/add", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody
	ResponseEntity<Void> doAdd(@Valid @RequestBody Card card) {
		if (service.add(card)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/card/update", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody
	ResponseEntity<Void> doUpdate(@Valid @RequestBody Card card) {
		if (service.update(card)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/card/getById/{cardId}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<Card> doGetById(@PathVariable("cardId") int cardId) {
		Card card = null;
		try {
			card = service.getById(cardId);
		} catch (Exception e) {
			return new ResponseEntity<Card>(HttpStatus.NOT_FOUND);
		}
		if (card != null) {
			return new ResponseEntity<Card>(card, HttpStatus.OK);
		} else {
			return new ResponseEntity<Card>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/card/getBySeri/{seri}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<Card> doGetById(@PathVariable("seri") String seri) {
		Card card = null;
		try {
			card = service.getBySeri(seri);
		} catch (Exception e) {
			return new ResponseEntity<Card>(HttpStatus.NOT_FOUND);
		}
		if (card != null) {
			return new ResponseEntity<Card>(card, HttpStatus.OK);
		} else {
			return new ResponseEntity<Card>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/card/add/{value}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<Integer> doAddCard(@PathVariable("value") int value) {
		int added = 0;
		for (int i = 0; i < 10; i++) {
			Card card = new Card();
			// card.setIsUsed(false);
			card.setValue(value);
			card.setSeri(randomSeri());
			try {
				service.add(card);
				added++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return new ResponseEntity<Integer>(added, HttpStatus.OK);
	}

	private String randomSeri() {
		int[] anArray;
		anArray = new int[10];
		for (int i = 0; i < anArray.length; i++) {
			anArray[i] = randomFill();
		}

		StringBuffer sb = new StringBuffer();
		for (int d : anArray) {
			sb.append(d);
		}

		return sb.toString();
	}

	private int randomFill() {
		Random rand = new Random();
		int randomNum = rand.nextInt(10);
		return randomNum;
	}
}
