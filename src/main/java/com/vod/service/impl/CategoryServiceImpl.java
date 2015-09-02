package com.vod.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vod.dao.CategoryDao;
import com.vod.model.Category;
import com.vod.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao dao;

	@Override
	public boolean add(Category cate) {
		return dao.add(cate);
	}

	@Override
	public boolean update(Category cate) {
		return dao.update(cate);
	}

	@Override
	public Category get(Integer id) {
		return dao.get(id);
	}

	@Override
	public List<Category> getAll() {
		return dao.getAll();
	}

	@Override
	public boolean deleleAll() {
		return dao.deleleAll();
	}

	@Override
	public Category getByName(String name) {
		return dao.getByName(name);
	}

}
