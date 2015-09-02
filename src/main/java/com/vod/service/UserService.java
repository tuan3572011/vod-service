package com.vod.service;

import java.util.List;

import com.vod.model.User;

public interface UserService {
	public boolean add(User user);
	public boolean update(User user);
	public boolean remove(User user);
	public User getByEmail(String email);
	public List<User> getAll();
}
