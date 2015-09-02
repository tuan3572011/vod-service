package com.vod.dao;

import java.util.List;

import com.vod.model.RechargeCard;

public interface RechargeDao {
	public boolean add(RechargeCard card);
	public boolean update(RechargeCard card);
	public RechargeCard getById(Integer id);
	public List<RechargeCard> getByUser(String email);
	
}
