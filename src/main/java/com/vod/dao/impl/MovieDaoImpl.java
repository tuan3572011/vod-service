package com.vod.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vod.dao.MovieDao;
import com.vod.model.Category;
import com.vod.model.FilterBean;
import com.vod.model.Movie;
import com.vod.service.CategoryService;

@Repository
public class MovieDaoImpl implements MovieDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private CategoryService cateService;

	@Override
	public boolean add(Movie movie) {
		try {
			sessionFactory.getCurrentSession().save(movie);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Movie movie) {
		try {
			sessionFactory.getCurrentSession().update(movie);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Movie get(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Movie movie = (Movie) session.createQuery("from Movie m where m.id = :id").setParameter("id", id)
				.uniqueResult();
		return movie;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Movie> movies = session.createQuery("from Movie m group by m.id").list();
		return movies;
	}

	@Override
	public List<Movie> getByCategory(String category) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getByYear(Integer year) {
		Session session = sessionFactory.getCurrentSession();
		String query = "from Movie m where m.publishedYear ='" + year + "' group by m.id";
		if (year == 2010) {
			query = "from Movie m where m.publishedYear <= '" + year + "' group by m.id";
		}
		List<Movie> movies = session.createQuery(query).list();
		return movies;
	}

	@Override
	public List<Movie> getByCountry(String country) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getByRate() {
		Session session = sessionFactory.getCurrentSession();
		List<Movie> movies = session.createQuery("from Movie m group by m.id order by m.rate DESC").list();
		return movies;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getByView() {
		Session session = sessionFactory.getCurrentSession();
		List<Movie> movies = session.createQuery("from Movie m group by m.id order by m.view DESC").list();
		return movies;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vod.dao.MovieDao#filterBy(java.lang.Integer, java.lang.Integer,
	 * java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Movie> filterBy(Integer orderId, Category category, Integer year, Integer countryId, Integer page) {
		Session session = sessionFactory.getCurrentSession();
		// from Cat as cat where cat.mate.name like '%s%'

		// select distinct u from SystemUser u
		// join u.userGroups g
		// join u.organisations o
		// where 3 in elements(g.permissions) and
		// o.id not in (?)

		StringBuilder sqlCommand = new StringBuilder("from Movie m");
		FilterBean filterBean = new FilterBean(orderId, category, year, countryId, page);
		if (filterBean.isSearchAny()) {
			sqlCommand.append(" WHERE " + category);
			if (filterBean.isSearchByCategory()) {
				sqlCommand.append("in elements (m.countries)");
			}
		}

		Query query = session.createQuery(sqlCommand.toString());

		if (year == 2010) {
			query = "from Movie m where m.publishedYear <= '" + year + "' group by m.id";
		}
		List<Movie> movies = session.createQuery(query).list();
		return movies;
	}
}
