package com.gs.miaosha.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gs.miaosha.dao.OrderInfoDao;
import com.gs.miaosha.entity.MiaoshaGoods;
import com.gs.miaosha.entity.MiaoshaOrder;
import com.gs.miaosha.entity.OrderInfo;
import com.gs.miaosha.entity.User;
import com.gs.miaosha.redis.MiaoshaOrderKey;
import com.gs.miaosha.redis.RedisService;
import com.gs.miaosha.service.MiaoshaGoodsService;
import com.gs.miaosha.service.MiaoshaOrderService;
import com.gs.miaosha.service.OrderInfoService;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

	@Autowired
	private OrderInfoDao orderInfoDao;
	
	@Autowired
	private MiaoshaOrderService miaoshaOrderService;
	
	@Autowired
	private MiaoshaGoodsService miaoshaGoodsService;
	
	@Autowired
	private RedisService redisService;
	
	@Override
	@Transactional
	public OrderInfo createOrder(User user ,long id) {
		MiaoshaGoods miaoshaGoods= miaoshaGoodsService.getMiaoGoods(id);
		OrderInfo order = new OrderInfo();
		order.setUserId(user.getId());
		order.setGoodsCount(1);
		order.setGoodsId(miaoshaGoods.getGoodsId());
		order.setGoodsName(miaoshaGoods.getGoodsName());
		order.setGoodsPrice(miaoshaGoods.getMiaoshaPrice());
		order.setStatus(0);
		order.setCreateDate(new Date());
		orderInfoDao.insert(order);
		MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
		miaoshaOrder.setGoodsId(miaoshaGoods.getId());
		miaoshaOrder.setOrderId(order.getId());
		miaoshaOrder.setUserId(user.getId());
		miaoshaOrderService.insert(miaoshaOrder);
		redisService.set(MiaoshaOrderKey.getKey(String.valueOf(user.getId()),String.valueOf(miaoshaGoods.getGoodsId())), miaoshaOrder);
		return order;
	}

}
