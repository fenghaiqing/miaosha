package com.gs.miaosha.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gs.miaosha.dao.MiaoshaOrderDao;
import com.gs.miaosha.entity.MiaoshaOrder;
import com.gs.miaosha.redis.MiaoshaOrderKey;
import com.gs.miaosha.redis.RedisService;
import com.gs.miaosha.service.MiaoshaOrderService;

@Service
public class MiaoshaOrderServiceImpl implements MiaoshaOrderService {

	@Autowired
	private MiaoshaOrderDao miaoshaOrderDao;
	@Autowired
	private RedisService redisService;
	
	@Override
	public MiaoshaOrder getMiaoshaOrderByGoodsId(String userId, long id) {
		MiaoshaOrder miaoshaOrder = redisService.get(MiaoshaOrderKey.getKey(String.valueOf(userId),String.valueOf(id)), MiaoshaOrder.class);
		if(miaoshaOrder!=null) {
			return miaoshaOrder;
		}else {
			return null;
		}
	}

	@Override
	@Transactional
	public void insert(MiaoshaOrder miaoshaOrder) {
		miaoshaOrderDao.insert(miaoshaOrder);
		
	}

}
