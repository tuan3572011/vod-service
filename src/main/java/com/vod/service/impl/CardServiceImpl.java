package com.vod.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vod.dao.CardDao;
import com.vod.model.Card;
import com.vod.service.CardService;

@Service
@Transactional
public class CardServiceImpl implements CardService {
	@Autowired
	private CardDao dao;

	@Override
	public boolean add(Card card) {
		return dao.add(card);
	}

	@Override
	public Card getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public Card getBySeri(String seri) {
		return dao.getBySeri(seri);
	}

	@Override
	public boolean update(Card card) {
		return dao.update(card);
	}

	@Override
	public List<Card> getAll() {
		return dao.getAll();
	}

	@Override
	public List<Card> allUnused() {
		return dao.allUnused();
	}

}
