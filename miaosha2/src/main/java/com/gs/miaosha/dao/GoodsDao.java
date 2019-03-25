package com.gs.miaosha.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gs.miaosha.entity.Goods;

@Mapper
public interface GoodsDao {
	
   /* int deleteByPrimaryKey(Long id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);*/

	@Update("update goods set goods_stock = #{goodsStock} where id = #{id} and goods_stock > 0")
    int updateByPrimaryKey(Goods record);
    
	@Select("select *from goods where id = #{id}")
	Goods getGoods(@Param("id") long goodsId);
}