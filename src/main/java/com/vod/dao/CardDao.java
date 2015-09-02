package com.vod.dao;

import java.util.List;

import com.vod.model.Card;

public interface CardDao {
	public boolean add(Card card);
	public boolean update(Card card);
	public Card getById(Integer id);
	public Card getBySeri(String seri);
	public List<Card> getAll();
	public List<Card> allUnused();
}
