package com.gs.miaosha.controller;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gs.miaosha.entity.MiaoshaGoods;
import com.gs.miaosha.entity.MiaoshaOrder;
import com.gs.miaosha.entity.User;
import com.gs.miaosha.rabbitmq.MQSender;
import com.gs.miaosha.redis.GoodsKey;
import com.gs.miaosha.redis.RedisService;
import com.gs.miaosha.result.CodeMsg;
import com.gs.miaosha.result.Result;
import com.gs.miaosha.service.MiaoshaGoodsService;
import com.gs.miaosha.service.MiaoshaOrderService;
import com.gs.miaosha.vo.GoodsVo;
import com.gs.miaosha.vo.MiaoshaMessage;

@Controller
@RequestMapping("/miaosha")
public class MiaoshaController implements InitializingBean {

	@Autowired
	private MiaoshaGoodsService miaoshaGoodsService;

	@Autowired
	private MiaoshaOrderService miaoshaOrderService;

	@Autowired
	private RedisService redsService;

	@Autowired
	private MQSender sender;

	@RequestMapping("/do_miaosha")
	@ResponseBody
	public Result<Object> miaosha(MiaoshaGoods miaoshaGoods, User user) {
		if (user == null) {
			return Result.ERROR(CodeMsg.NO_LONGIN);
		}
		// 判断库存
		long stock = redsService.get(GoodsKey.getKey(String.valueOf(miaoshaGoods.getId())),long.class);
		if ((stock-1) < 0) {
			setIsOver(miaoshaGoods.getId(),true);
			return Result.ERROR(CodeMsg.STOCK_COUNT_LESS);
		}else {
			setIsOver(miaoshaGoods.getId(),false);
		}
		// 判断是否已经秒杀到
		MiaoshaOrder order = miaoshaOrderService.getMiaoshaOrderByGoodsId(user.getId(), miaoshaGoods.getGoodsId());
		if (order != null) {
			return Result.ERROR(CodeMsg.REPEAT_BUY_ERROR);
		}
		redsService.decr(GoodsKey.getKey(String.valueOf(miaoshaGoods.getId())).getPrefix());
		// 入队
		MiaoshaMessage message = new MiaoshaMessage(user, miaoshaGoods);
		sender.sendMiaoshaMessage(message);
		return Result.SUCCESS(0);
	}


	@RequestMapping("/miaoshaResult")
	@ResponseBody
	public Result<Object> miaoshaResult(User user,  MiaoshaGoods miaoshaGoods) {
		// 查询是否生成订单
		MiaoshaOrder order = miaoshaOrderService.getMiaoshaOrderByGoodsId(user.getId(), miaoshaGoods.getGoodsId());
		if (order != null) {// 已经生成订单 状态 1：秒杀成功
			return Result.SUCCESS(1);
		}else {// 未生成订单 
			//判断库存
			boolean isOver = getIsOver(miaoshaGoods.getId());
			if(isOver) { //库存不足 状态-1：秒杀失败；
				return Result.SUCCESS(-1);
			}else {// 还有库存 状态0：订单排队中
				return Result.SUCCESS(0);
			}
		}
	}

	private boolean getIsOver(long id) {
		return redsService.get(GoodsKey.getKey("ISOVER",String.valueOf(id)), boolean.class);
	}
	private void setIsOver(long id,boolean flag) {
		redsService.set(GoodsKey.getKey("ISOVER",String.valueOf(id)), flag);
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		List<GoodsVo> list = miaoshaGoodsService.miaoshaGoodsList();
		if (list != null && list.size() > 0) {
			for (GoodsVo goods : list) {
				redsService.set(GoodsKey.getKey(String.valueOf(goods.getId())), goods.getStockCount());
			}
		}
	}
	
}
