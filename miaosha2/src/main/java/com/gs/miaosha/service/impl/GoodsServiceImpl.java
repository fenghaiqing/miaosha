package com.gs.miaosha.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gs.miaosha.dao.GoodsDao;
import com.gs.miaosha.entity.Goods;
import com.gs.miaosha.service.GoodsService;
import com.gs.miaosha.vo.GoodsVo;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsDao goodsDao;
	
	@Override
	public Goods getGoods(long goodsId) {
		return goodsDao.getGoods(goodsId);
	}

	@Override
	@Transactional
	public void updateByPrimaryKey(GoodsVo goods) {
		Goods goodsInfo  = goodsDao.getGoods(goods.getGoodsId());
		goodsInfo.setGoodsStock(goodsInfo.getGoodsStock()-1);
		goodsDao.updateByPrimaryKey(goodsInfo);
	}

}
