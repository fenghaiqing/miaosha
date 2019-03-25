package com.gs.miaosha.service;

import com.gs.miaosha.entity.Goods;
import com.gs.miaosha.vo.GoodsVo;

public interface GoodsService {

	Goods getGoods(long goodsId);

	void updateByPrimaryKey(GoodsVo goodsInfo);

}
