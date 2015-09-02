package com.vod.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vod.dao.MovieDao;
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
		Movie movie = (Movie) session
				.createQuery("from Movie m where m.id = :id")
				.setParameter("id", id).uniqueResult();
		return movie;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Movie> movies = session.createQuery("from Movie m group by m.id")
				.list();
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
		String query = "from Movie m where m.publishedYear ='" + year
				+ "' group by m.id";
		if (year == 2010) {
			query = "from Movie m where m.publishedYear <= '" + year
					+ "' group by m.id";
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
		List<Movie> movies = session.createQuery(
				"from Movie m group by m.id order by m.rate DESC").list();
		return movies;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getByView() {
		Session session = sessionFactory.getCurrentSession();
		List<Movie> movies = session.createQuery(
				"from Movie m group by m.id order by m.view DESC").list();
		return movies;
	}

}
