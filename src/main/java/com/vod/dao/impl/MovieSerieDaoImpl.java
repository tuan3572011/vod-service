package com.vod.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vod.dao.MovieSerieDao;
import com.vod.model.MovieSerie;

@Repository
public class MovieSerieDaoImpl implements MovieSerieDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean add(MovieSerie movie) {
		try {
			sessionFactory.getCurrentSession().save(movie);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(MovieSerie movie) {
		try {
			sessionFactory.getCurrentSession().update(movie);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean remove(MovieSerie movie) {
		try {
			sessionFactory.getCurrentSession().delete(movie);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public MovieSerie getById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		MovieSerie movie = (MovieSerie) session
				.createQuery("from MovieSerie m where m.id = :id")
				.setParameter("id", id).uniqueResult();
		return movie;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MovieSerie> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<MovieSerie> movies = session.createQuery(
				"from MovieSerie m group by m.id").list();
		return movies;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MovieSerie> getByRate() {
		Session session = sessionFactory.getCurrentSession();
		List<MovieSerie> movieSeries = session.createQuery(
				"from MovieSerie m group by m.id order by m.rate DESC").list();
		return movieSeries;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MovieSerie> getByView() {
		Session session = sessionFactory.getCurrentSession();
		List<MovieSerie> movieSeries = session.createQuery(
				"from MovieSerie m group by m.id order by m.view DESC").list();
		return movieSeries;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MovieSerie> getByYear(Integer year) {
		Session session = sessionFactory.getCurrentSession();
		String query = "from MovieSerie m where m.publishedYear = '" + year
				+ "' group by m.id";
		if (year == 2010) {
			query = "from MovieSerie m where m.publishedYear <= '" + year
					+ "' group by m.id";
		}
		List<MovieSerie> movies = session.createQuery(query).list();
		return movies;
	}

}
