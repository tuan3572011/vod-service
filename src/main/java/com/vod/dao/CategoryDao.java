package com.vod.dao;

import java.util.List;

import com.vod.model.Category;

public interface CategoryDao {
	public boolean add(Category cate);
	public boolean update(Category cate);
	public boolean deleleAll();
	public Category get(Integer id);
	public Category getByName(String name);
	public List<Category> getAll();
}
