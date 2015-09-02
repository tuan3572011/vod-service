package com.vod.service;

import java.util.List;

import com.vod.model.Category;

public interface CategoryService {
	public boolean add(Category cate);
	public boolean deleleAll();
	public boolean update(Category cate);
	public Category get(Integer id);
	public Category getByName(String name);
	public List<Category> getAll();
}
