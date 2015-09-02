package com.vod.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vod.dao.CardDao;
import com.vod.model.Card;

@Repository
public class CardDaoImpl implements CardDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean add(Card card) {
		try {
			sessionFactory.getCurrentSession().save(card);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Card getById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Card card = (Card) session.createQuery("from Card c where c.id =:id")
				.setParameter("id", id).uniqueResult();
		return card;
	}

	@Override
	public Card getBySeri(String seri) {
		Session session = sessionFactory.getCurrentSession();
		Card card = (Card) session
				.createQuery("from Card c where c.seri =:seri")
				.setParameter("seri", seri).uniqueResult();
		return card;
	}

	@Override
	public boolean update(Card card) {
		try {
			sessionFactory.getCurrentSession().update(card);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Card> getAll() {
		return sessionFactory.getCurrentSession().createCriteria(Card.class)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Card> allUnused() {
		Session session = sessionFactory.getCurrentSession();
		List<Card> cards = session.createQuery(
				"from Card c where c.isUsed is false").list();
		return cards;
	}

}
