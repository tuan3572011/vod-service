package com.vod.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vod.dao.RechargeDao;
import com.vod.model.RechargeCard;
import com.vod.service.RechargeService;

@Service
@Transactional
public class RechargeServiceImpl implements RechargeService{
	@Autowired
	private RechargeDao dao;
	
	@Override
	public boolean add(RechargeCard card) {
		return dao.add(card);
	}

	@Override
	public boolean update(RechargeCard card) {
		return dao.update(card);
	}

	@Override
	public RechargeCard getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public List<RechargeCard> getByUser(String email) {
		return dao.getByUser(email);
	}

}
