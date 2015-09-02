package com.vod.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vod.dao.RegisMovieDao;
import com.vod.model.RegisterFilm;

@Repository
public class RegisMovieDaoImpl implements RegisMovieDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean add(RegisterFilm regis) {
		try {
			sessionFactory.getCurrentSession().save(regis);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public RegisterFilm getById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		RegisterFilm regis = (RegisterFilm) session
				.createQuery("from RegisterFilm e where e.id = :id")
				.setParameter("id", id).uniqueResult();
		return regis;
	}

	@Override
	public RegisterFilm getByUser(String email) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(RegisterFilm.class, "regis");
		c.createAlias("regis.user", "user");
		c.add(Restrictions.eq("user.email", email));
		c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		RegisterFilm regis = (RegisterFilm) c.uniqueResult();
		return regis;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegisterFilm> getListByUser(String email) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(RegisterFilm.class);
		Criteria supCrit = c.createCriteria("user");
		supCrit.add(Restrictions.eq("email", email));
		List<RegisterFilm> regis = c.list();
		List<RegisterFilm> rgs = new ArrayList<RegisterFilm>();
		int idtmp = -1;
		for (RegisterFilm r : regis) {
			if (r.getId() != idtmp) {
				rgs.add(r);
			}
			idtmp = r.getId();
		}
		return rgs;
	}

}
