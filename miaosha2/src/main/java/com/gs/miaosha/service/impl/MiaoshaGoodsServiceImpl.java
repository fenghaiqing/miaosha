package com.gs.miaosha.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gs.miaosha.dao.MiaoshaGoodsDao;
import com.gs.miaosha.entity.MiaoshaGoods;
import com.gs.miaosha.entity.OrderInfo;
import com.gs.miaosha.entity.User;
import com.gs.miaosha.service.MiaoshaGoodsService;
import com.gs.miaosha.service.OrderInfoService;
import com.gs.miaosha.vo.GoodsVo;

@Service
public class MiaoshaGoodsServiceImpl implements MiaoshaGoodsService {

	@Autowired
	private MiaoshaGoodsDao miaoshaGoodsDao;
	
	@Autowired
	private OrderInfoService orderInfoService;
	
	@Override
	public List<GoodsVo> miaoshaGoodsList() {
		return miaoshaGoodsDao.miaoshaGoodsList();
		 
	}
	@Override
	public GoodsVo getMiaoshaGoodsById(long id) {
		return miaoshaGoodsDao.getMiaoshaGoodsById(id);
	}
	
	@Override
	@Transactional
	public OrderInfo miaosha(User user,MiaoshaGoods miaoshaGoods) {
		// 修改秒杀商品库存
		boolean isupdate = updateMiaoshGoods(miaoshaGoods.getId());
		if(isupdate) {
			// 修改商品库存
			//goodsservice.updateByPrimaryKey(goods);
			// 生成订单信息
			return orderInfoService.createOrder(user,miaoshaGoods.getId());
		}
		return null;
		
		
	}

	@Transactional
	public boolean updateMiaoshGoods(long id){
		int ret = miaoshaGoodsDao.updateMiaoshGoods(id);
		return ret > 0;
	}
	
	@Override
	public MiaoshaGoods getMiaoGoods(long id) {
		return  miaoshaGoodsDao.getMiaoGoods(id);
	}
}
