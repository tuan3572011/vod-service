package com.vod.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vod.dao.UserDao;
import com.vod.model.User;
import com.vod.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

	@Override
	public boolean add(User user) {
		return dao.add(user);
	}

	@Override
	public boolean update(User user) {
		return dao.update(user);
	}

	@Override
	public boolean remove(User user) {
		return dao.remove(user);
	}

	@Override
	public User getByEmail(String email) {
		return dao.getByEmail(email);
	}

	@Override
	public List<User> getAll() {
		return dao.getAll();
	}

}
