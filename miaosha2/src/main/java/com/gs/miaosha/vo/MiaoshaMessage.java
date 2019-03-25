package com.gs.miaosha.vo;

import com.gs.miaosha.entity.MiaoshaGoods;
import com.gs.miaosha.entity.User;

public class MiaoshaMessage {

	private User user;
	
	private long id;
	
	private MiaoshaGoods miaoshaGoods;

	public MiaoshaMessage() {
		super();
	}

	public MiaoshaMessage(User user, MiaoshaGoods miaoshaGoods) {
		super();
		this.user = user;
		this.miaoshaGoods = miaoshaGoods;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MiaoshaGoods getMiaoshaGoods() {
		return miaoshaGoods;
	}

	public void setMiaoshaGoods(MiaoshaGoods miaoshaGoods) {
		this.miaoshaGoods = miaoshaGoods;
	}
	
	
}
