package com.vod.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vod.dao.MovieDao;
import com.vod.model.Category;
import com.vod.model.Country;
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


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vod.dao.MovieDao#filterBy(java.lang.Integer, java.lang.Integer,
	 * java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Movie> filterBy(Integer orderId, Category category, Integer year, Country country, Integer page) {
		Session session = sessionFactory.getCurrentSession();
		// from Cat as cat where cat.mate.name like '%s%'

		// select distinct u from SystemUser u
		// join u.userGroups g
		// join u.organisations o
		// where 3 in elements(g.permissions) and
		// o.id not in (?)

		StringBuilder sqlCommand = new StringBuilder("from Movie m");
		FilterBean filterBean = new FilterBean(orderId, category, year, country, page);
		int countSearchBy = 0;
		if (filterBean.isSearchAny()) {
			sqlCommand.append(" WHERE ");
			if (filterBean.isSearchByCategory()) {
				countSearchBy++;
				this.addANDCommand(countSearchBy, sqlCommand);
				sqlCommand.append(" :category in elements (m.categories) ");

			}
			if (filterBean.isSearchByCountry()) {
				countSearchBy++;
				this.addANDCommand(countSearchBy, sqlCommand);
				sqlCommand.append(" :country in elements (m.countries) ");
			}
			if (filterBean.isSearchByYear()) {
				countSearchBy++;
				this.addANDCommand(countSearchBy, sqlCommand);
				String query = " m.publishedYear =:year ";
				if (year == 2010) {
					query = " m.publishedYear <=:year ";
				}
				sqlCommand.append(query);
			}
			if (filterBean.isSearchByOrder()) {
				countSearchBy++;
				if (orderId == 1) {
					sqlCommand.append("  order by m.view DESC");
				} else if (orderId == 2) {
					sqlCommand.append("  order by m.rate DESC");
				}
			}

		}
		Query query = session.createQuery(sqlCommand.toString());
		if (filterBean.isSearchByCategory()) {
			query.setEntity("category", category);
		}
		if (filterBean.isSearchByCountry()) {
			query.setEntity("country", country);

		}
		if (filterBean.isSearchByYear()) {
			query.setInteger("year", year);
		}
		query.setFirstResult(page * 16 - 16);
		query.setMaxResults(16);
		List<Movie> movies = query.list();
		return movies;
	}

	private void addANDCommand(int count, StringBuilder sqlCommand) {
		if (count > 1) {
			sqlCommand.append(" AND ");
		}
	}
}
