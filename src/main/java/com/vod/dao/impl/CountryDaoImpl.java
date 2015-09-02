package com.vod.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vod.dao.CountryDao;
import com.vod.model.Country;

@Repository
public class CountryDaoImpl implements CountryDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean add(Country country) {
		try {
			sessionFactory.getCurrentSession().save(country);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Country get(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		List<Country> countries = session
				.createQuery("from Country c where c.id = :id")
				.setParameter("id", id).list();
		return countries.size() > 0 ? (Country) countries.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Country> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Country> countries = session.createQuery(
				"from Country c group by c.id order by c.name").list();
		return countries;
	}

	@Override
	public boolean deleleAll() {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.createQuery("delete from Country").executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Country getByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		Country country = (Country) session
				.createQuery("from Country c where c.name = :name")
				.setParameter("name", name).uniqueResult();
		return country;
	}

}
