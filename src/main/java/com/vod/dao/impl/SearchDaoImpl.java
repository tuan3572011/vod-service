package com.vod.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vod.common.VodCommon;
import com.vod.dao.SearchDao;
import com.vod.model.Category;
import com.vod.model.Director;
import com.vod.model.Film;
import com.vod.model.MovieSearch;
import com.vod.model.Starring;

@Repository
public class SearchDaoImpl implements SearchDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<MovieSearch> search(String type, String data) {
		List<MovieSearch> movies = new ArrayList<MovieSearch>();
		String name = data;
		String decodedDataUsingUTF8 = VodCommon.convertToUTF8(name);

		if (type.equalsIgnoreCase("movie")) {
			Session session = sessionFactory.getCurrentSession();

			List<Film> films = session
					.createQuery(
							"from Film m where m.vnName like :namea or m.engName like :namea group by m.id")
					.setParameter("namea", decodedDataUsingUTF8).list();
			for (Film f : films) {
				if (f.getType().equalsIgnoreCase("movie")) {
					MovieSearch moS = new MovieSearch();
					moS.setId(f.getId());
					moS.setType(f.getType());
					moS.setEngName(f.getEngName());
					moS.setName(f.getName());
					moS.setVnName(f.getVnName());
					moS.setImage(f.getImage());
					moS.setPublishedYear(f.getPublishedYear());
					moS.setImdb(f.getImdb());
					moS.setTrailer(f.getTrailer());
					movies.add(moS);
				}
			}
		} else if (type.equalsIgnoreCase("director")) {
			Integer directorId = -1;
			try {
				directorId = Integer.parseInt(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Session session = sessionFactory.getCurrentSession();
			Criteria crit = session.createCriteria(Film.class);
			Criteria supCrit = crit.createCriteria("director");
			supCrit.add(Restrictions.eq("id", directorId));
			List<Film> films = crit.list();
			Integer idtmp = -1;
			for (Film f : films) {
				if (f.getType().equalsIgnoreCase("movie")) {
					if (f.getId() != idtmp) {
						MovieSearch moS = new MovieSearch();
						moS.setId(f.getId());
						moS.setType(f.getType());
						moS.setEngName(f.getEngName());
						moS.setName(f.getName());
						moS.setVnName(f.getVnName());
						moS.setImage(f.getImage());
						moS.setPublishedYear(f.getPublishedYear());
						moS.setImdb(f.getImdb());
						moS.setTrailer(f.getTrailer());
						movies.add(moS);
						idtmp = f.getId();
					}
				}
			}
		} else if (type.equalsIgnoreCase("starring")) {
			Integer starId = -1;
			try {
				starId = Integer.parseInt(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Session session = sessionFactory.getCurrentSession();
			Starring star = (Starring) session
					.createQuery("from Starring s where s.id = :id")
					.setParameter("id", starId).uniqueResult();
			if (star != null) {
				Set<Film> films = star.getFilms();
				Iterator<Film> iterator = films.iterator();
				while (iterator.hasNext()) {
					Film f = iterator.next();
					if (f.getType().equalsIgnoreCase("movie")
							|| f.getType().equalsIgnoreCase("serie")) {
						MovieSearch moS = new MovieSearch();
						moS.setId(f.getId());
						moS.setType(f.getType());
						moS.setEngName(f.getEngName());
						moS.setName(f.getName());
						moS.setVnName(f.getVnName());
						moS.setImage(f.getImage());
						moS.setPublishedYear(f.getPublishedYear());
						moS.setImdb(f.getImdb());
						moS.setTrailer(f.getTrailer());
						movies.add(moS);
					}
				}
			}
		} else if (type.equalsIgnoreCase("all")) {
			Session session = sessionFactory.getCurrentSession();
			List<Film> films = session.createQuery("from Film f group by f.id")
					.list();
			System.out.println(decodedDataUsingUTF8);
			for (Film f : films) {
				System.out.println(f.getVnName());
				boolean isTrue = false;
				if (f.getType().equalsIgnoreCase("movie")
						|| f.getType().equalsIgnoreCase("serie")) {

					Set<Starring> starrings = f.getStarrings();
					if ((f.getVnName().toLowerCase()).matches("(.*)"
							+ decodedDataUsingUTF8.toLowerCase() + "(.*)")
							|| f.getEngName()
									.toLowerCase()
									.matches(
											"(.*)"
													+ decodedDataUsingUTF8
															.toLowerCase()
													+ "(.*)")
							|| f.getDirector()
									.getName()
									.toLowerCase()
									.matches(
											"(.*)"
													+ decodedDataUsingUTF8
															.toLowerCase()
													+ "(.*)")) {
						isTrue = true;
					}
					if (!isTrue) {
						for (Starring s : starrings) {
							System.out.println("Starring---" + s.getName());
							if (s.getName()
									.toLowerCase()
									.matches(
											"(.*)"
													+ decodedDataUsingUTF8
															.toLowerCase()
													+ "(.*)")) {
								isTrue = true;
							}
						}
					}
					if (isTrue) {
						MovieSearch moS = new MovieSearch();
						moS.setId(f.getId());
						moS.setType(f.getType());
						moS.setEngName(f.getEngName());
						moS.setName(f.getName());
						moS.setVnName(f.getVnName());
						moS.setImage(f.getImage());
						moS.setPublishedYear(f.getPublishedYear());
						moS.setImdb(f.getImdb());
						moS.setTrailer(f.getTrailer());
						movies.add(moS);
					}
				}
			}
		}

		return movies;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Film> allFilm() {
		Session session = sessionFactory.getCurrentSession();
		List<Film> films = session.createQuery("from Film f group by f.id")
				.list();

		return films;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Director> allDirector() {
		return sessionFactory.getCurrentSession()
				.createCriteria(Director.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MovieSearch> topIMDB() {
		List<MovieSearch> searchs = new ArrayList<MovieSearch>();
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from Film m group by m.id")
				.addQueryHint(" order by m.imdb asc");
		List<Film> films = q.setMaxResults(20).list();

		// List<Film> films = session.createCriteria(Film.class)
		// .addOrder(Order.desc("imdb")).setMaxResults(10).list();
		for (Film f : films) {
			if (f.getType().equals("movie") || f.getType().equals("serie")) {
				MovieSearch moS = new MovieSearch();
				moS.setId(f.getId());
				moS.setType(f.getType());
				moS.setEngName(f.getEngName());
				moS.setName(f.getName());
				moS.setVnName(f.getVnName());
				moS.setImage(f.getImage());
				moS.setPublishedYear(f.getPublishedYear());
				moS.setImdb(f.getImdb());
				moS.setTrailer(f.getTrailer());
				searchs.add(moS);
			}
		}
		return searchs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MovieSearch> topNew() {
		List<MovieSearch> searchs = new ArrayList<MovieSearch>();
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from Film m group by m.id")
				.addQueryHint(" order by m.updatedDate asc");
		List<Film> films = q.setMaxResults(20).list();

		// List<Film> films = session.createCriteria(Film.class)
		// .addOrder(Order.desc("updatedDate")).setMaxResults(10).list();
		for (Film f : films) {
			if (f.getType().equals("movie") || f.getType().equals("serie")) {
				MovieSearch moS = new MovieSearch();
				moS.setId(f.getId());
				moS.setType(f.getType());
				moS.setEngName(f.getEngName());
				moS.setName(f.getName());
				moS.setVnName(f.getVnName());
				moS.setImage(f.getImage());
				moS.setPublishedYear(f.getPublishedYear());
				moS.setImdb(f.getImdb());
				moS.setTrailer(f.getTrailer());
				searchs.add(moS);
			}
		}
		return searchs;
	}

	@Override
	public Film getFilmById(Integer movieId) {
		Session session = sessionFactory.getCurrentSession();
		Film film = (Film) session.createQuery("from Film f where f.id = :id")
				.setParameter("id", movieId).uniqueResult();
		return film;
	}

	@Override
	public List<Film> getByCategory(String cate) {
		Session session = sessionFactory.getCurrentSession();
		Category category = (Category) session
				.createQuery("from Category c where c.name = :name")
				.setParameter("name", cate).uniqueResult();
		Set<Film> films = category.getFilms();
		List<Film> fs = new ArrayList<Film>();
		Iterator<Film> iterator = films.iterator();
		while (iterator.hasNext()) {
			Film f = iterator.next();
			fs.add(f);
		}
		return fs;
	}

}
