package com.vod.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vod.dao.StarringDao;
import com.vod.model.Starring;

@Repository
public class StarringDaoImpl implements StarringDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean add(Starring starring) {
		try {
			sessionFactory.getCurrentSession().save(starring);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Starring starring) {
		try {
			sessionFactory.getCurrentSession().update(starring);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Starring get(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Starring starring = (Starring) session
				.createQuery("from Starring s where s.id = :id")
				.setParameter("id", id).uniqueResult();
		return starring;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Starring> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Starring> stars = session.createQuery(
				"from Starring s group by s.id").list();
		return stars;

	}

	@Override
	public boolean deleteAll() {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.createQuery("delete from Starring").executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
