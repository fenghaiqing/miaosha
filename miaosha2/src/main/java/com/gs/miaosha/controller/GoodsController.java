package com.gs.miaosha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gs.miaosha.entity.MiaoshaOrder;
import com.gs.miaosha.entity.User;
import com.gs.miaosha.service.MiaoshaGoodsService;
import com.gs.miaosha.service.MiaoshaOrderService;
import com.gs.miaosha.vo.GoodsVo;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	
	@Autowired
	private MiaoshaGoodsService miaoshaGoodsService;
	
	@Autowired
	private MiaoshaOrderService miaoshaOrderService;
	
	
	@RequestMapping("/miaoshaList")
	public String goodsList(Model model) {
		List<GoodsVo> list = miaoshaGoodsService.miaoshaGoodsList();
		model.addAttribute("goodsList", list);
		return "goods_list";
	}
	
	@RequestMapping("/detail/{id}")
	public String goodsDetail(Model model,@PathVariable(name="id") long id,User user) {
		GoodsVo goods = miaoshaGoodsService.getMiaoshaGoodsById(id);
		model.addAttribute("goods", goods);
		model.addAttribute("user", user);
		
		long startDate = goods.getStartDate().getTime();// 开始时间
		long endDate = goods.getEndDate().getTime();// 结束时间
		long now = System.currentTimeMillis(); // 当前时间
		
		int miaoshaStatus = 0;
		int remainSeconds = 0;
		if(now < startDate) {// 秒杀没开始  倒计时
			miaoshaStatus = 0;
			remainSeconds = (int)(startDate -now)/1000;
		} else if(now>endDate) { // 秒杀结束
			miaoshaStatus = 2;
			remainSeconds = -1;
		}else { // 秒杀进行中
			miaoshaStatus = 1;
			remainSeconds = 0;
		}
	
		model.addAttribute("miaoshaStatus", miaoshaStatus);
		model.addAttribute("remainSeconds", remainSeconds);
		model.addAttribute("endDate",endDate);
		return "goods_detail";
	}
	

}
