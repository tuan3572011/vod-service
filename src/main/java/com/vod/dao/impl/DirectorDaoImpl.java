package com.vod.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vod.dao.DirectorDao;
import com.vod.model.Director;

@Repository
public class DirectorDaoImpl implements DirectorDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean add(Director director) {
		try {
			sessionFactory.getCurrentSession().save(director);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Director director) {
		try {
			sessionFactory.getCurrentSession().update(director);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Director get(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		List<Director> directors = session
				.createQuery("from Director d where d.id = :id")
				.setParameter("id", id).list();
		return directors.size() > 0 ? (Director) directors.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Director> getAll() {
		return sessionFactory.getCurrentSession()
				.createCriteria(Director.class).list();
	}

	@Override
	public boolean deleteAll() {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.createQuery("delete from Director").executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
