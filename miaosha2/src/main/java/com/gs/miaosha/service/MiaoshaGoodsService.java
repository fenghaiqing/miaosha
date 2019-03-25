package com.gs.miaosha.service;

import java.util.List;

import com.gs.miaosha.entity.MiaoshaGoods;
import com.gs.miaosha.entity.OrderInfo;
import com.gs.miaosha.entity.User;
import com.gs.miaosha.vo.GoodsVo;

public interface MiaoshaGoodsService {

	List<GoodsVo> miaoshaGoodsList();

	GoodsVo getMiaoshaGoodsById(long id);

	OrderInfo miaosha(User user,MiaoshaGoods miaoshaGoods);
	
	boolean updateMiaoshGoods(long id);

	MiaoshaGoods getMiaoGoods(long id);
}
