package com.vod.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vod.dao.MovieOddDao;
import com.vod.model.MovieOdd;

@Repository
public class MovieOddDaoImpl implements MovieOddDao {
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<MovieOdd> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<MovieOdd> odds = session.createQuery(
				"from MovieOdd m group by m.id").list();
		return odds;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MovieOdd> getByView() {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from MovieOdd m order by m.view")
				.addQueryHint(" group by m.id");
		List<MovieOdd> odds = q.setMaxResults(16).list();
		return odds;
	}

	@Override
	public List<MovieOdd> getByComment() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MovieOdd> getByRate() {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from MovieOdd m order by m.rate")
				.addQueryHint(" group by m.id");
		List<MovieOdd> odds = q.setMaxResults(16).list();
		return odds;
	}

	@Override
	public MovieOdd get(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		MovieOdd odd = (MovieOdd) session
				.createQuery("from MovieOdd m where m.id = :id")
				.setParameter("id", id).uniqueResult();
		return odd;
	}
}
