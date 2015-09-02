package com.vod.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vod.dao.CommentDao;
import com.vod.model.Comment;

@Repository
public class CommentDaoImpl implements CommentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean add(Comment comment) {
		try {
			sessionFactory.getCurrentSession().save(comment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Comment getById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Comment comment = (Comment) session.createQuery(
				"from Comment c where c.id =:id").uniqueResult();
		return comment;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getByUser(String email) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Comment.class, "comment");
		c.createAlias("comment.user", "user");
		c.add(Restrictions.eq("user.email", email));
		c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		List<Comment> comments = c.list();
		return comments;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getByFilm(Integer filmId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Comment.class, "comment");
		c.createAlias("comment.movieOdd", "movieOdd");
		c.add(Restrictions.eq("movieOdd.id", filmId));
		c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		List<Comment> comments = c.list();
		return comments;
	}

}
