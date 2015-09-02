package com.vod.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vod.dao.EpisodeDao;
import com.vod.model.Episodes;

@Repository
public class EpisodeDaoImpl implements EpisodeDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean add(Episodes episode) {
		try {
			sessionFactory.getCurrentSession().save(episode);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Episodes episode) {
		try {
			sessionFactory.getCurrentSession().update(episode);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean remove(Episodes episode) {
		try {
			sessionFactory.getCurrentSession().delete(episode);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Episodes getById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Episodes episode = (Episodes) session
				.createQuery("from Episodes e where e.id = :id")
				.setParameter("id", id).uniqueResult();
		return episode;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Episodes> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Episodes> episodes = session.createQuery("from Episodes").list();
		return episodes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Episodes getEpisodeBySerie(Integer serieId, Integer noEpisode) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(Episodes.class);
		Criteria supCrit = crit.createCriteria("movieSerie");
		supCrit.add(Restrictions.eq("id", serieId));
		List<Episodes> eps = crit.list();
		for (Episodes e : eps) {
			if (e.getNoEpisode() == noEpisode) {
				return e;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Episodes> getListBySerie(Integer serieId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(Episodes.class);
		Criteria supCrit = crit.createCriteria("movieSerie");
		supCrit.add(Restrictions.eq("id", serieId));
		List<Episodes> eps = crit.list();
		Set<Episodes> setEp = new HashSet<Episodes>();
		setEp.addAll(eps);
		List<Episodes> episodes = new ArrayList<Episodes>();
		episodes.addAll(setEp);
		Collections.sort(episodes, new Comparator<Episodes>() {
			@Override
			public int compare(Episodes ep1, Episodes ep2) {
				return (ep1.getNoEpisode() > ep2.getNoEpisode()) ? 1 : -1;
			};
		});

		// System.out.println(eps.get(0).toString());
		return episodes;
	}

}
