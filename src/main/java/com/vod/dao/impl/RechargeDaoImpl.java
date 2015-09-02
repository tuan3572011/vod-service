package com.vod.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vod.dao.RechargeDao;
import com.vod.model.RechargeCard;

@Repository
public class RechargeDaoImpl implements RechargeDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean add(RechargeCard card) {
		try {
			sessionFactory.getCurrentSession().save(card);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(RechargeCard card) {
		try {
			sessionFactory.getCurrentSession().update(card);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public RechargeCard getById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		RechargeCard recharge = (RechargeCard) session.createQuery(
				"from RechargeCard r where r.id =:id").uniqueResult();
		return recharge;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RechargeCard> getByUser(String email) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(RechargeCard.class, "recharge");
		c.createAlias("recharge.user", "user");
		c.add(Restrictions.eq("user.email", email));
		c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		List<RechargeCard> recharges = c.list();
		return recharges;
	}

}
