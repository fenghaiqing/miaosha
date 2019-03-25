package com.gs.miaosha.service;

import com.gs.miaosha.entity.MiaoshaOrder;

public interface MiaoshaOrderService {

	MiaoshaOrder getMiaoshaOrderByGoodsId(String userId, long id);

	void insert(MiaoshaOrder miaoshaOrder);
}
