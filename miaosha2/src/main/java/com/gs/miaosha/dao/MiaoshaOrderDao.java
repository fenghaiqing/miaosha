package com.gs.miaosha.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.gs.miaosha.entity.MiaoshaOrder;

@Mapper
public interface MiaoshaOrderDao {

	@Select("select * from miaosha_order where user_id= #{userId} and goods_id=#{id}")
	MiaoshaOrder getMiaoshaOrderByGoodsId(@Param("userId") String userId,@Param("id") long id);

	@Insert("insert into miaosha_order(user_id,order_id, goods_id) values(#{userId},#{orderId},#{goodsId})")
	void insert(MiaoshaOrder miaoshaOrder);
}