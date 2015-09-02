package com.vod.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vod.dao.CategoryDao;
import com.vod.model.Category;

@Repository
public class CategoryDaoImpl implements CategoryDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean add(Category cate) {
		try {
			sessionFactory.getCurrentSession().save(cate);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Category cate) {
		try {
			sessionFactory.getCurrentSession().update(cate);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Category get(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		List<Category> cates = session
				.createQuery("from Category c where c.id = :id")
				.setParameter("id", id).list();
		return cates.size() > 0 ? (Category) cates.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Category> cates = session.createQuery(
				"from Category c group by c.id order by c.name asc").list();
		return cates;
	}

	@Override
	public boolean deleleAll() {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.createQuery("delete from Category").executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Category getByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		Category cate = (Category) session
				.createQuery("from Category c where c.name = :name")
				.setParameter("name", name).uniqueResult();
		return cate;
	}

}
