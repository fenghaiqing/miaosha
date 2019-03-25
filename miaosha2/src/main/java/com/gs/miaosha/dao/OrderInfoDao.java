package com.gs.miaosha.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;

import com.gs.miaosha.entity.OrderInfo;
@Mapper
public interface OrderInfoDao {
	
	@Insert("insert into order_info(user_id,goods_id,goods_count,goods_price,goods_name,order_channel,status,create_date)"
			+ "values(#{userId},#{goodsId},#{goodsCount},#{goodsPrice},#{goodsName},#{orderChannel},#{status},#{createDate})")
    @SelectKey(keyColumn="id",keyProperty="id",resultType=long.class,before=false,statement="select last_insert_id()")
	long insert(OrderInfo order);
	
    OrderInfo selectByPrimaryKey(Long id);
}