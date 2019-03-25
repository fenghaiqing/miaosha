package com.gs.miaosha.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gs.miaosha.entity.MiaoshaGoods;
import com.gs.miaosha.vo.GoodsVo;
@Mapper
public interface MiaoshaGoodsDao {
   /* int deleteByPrimaryKey(Long id);

    int insert(MiaoshaGoods record);

    int insertSelective(MiaoshaGoods record);

    MiaoshaGoods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MiaoshaGoods record);

    int updateByPrimaryKey(MiaoshaGoods record);*/

	@Select("select m.id,m.goods_id,m.miaosha_price,m.stock_count,g.goods_name,g.goods_title,"
			+ "g.goods_img,g.goods_price from miaosha_goods m "
			+ "left join goods g on m.goods_id = g.id where m.stock_count>0 ")
	List<GoodsVo> miaoshaGoodsList();

	@Select("select m.id,m.miaosha_price,g.goods_title,g.goods_img,g.goods_price,m.start_date,m.end_date "
			+ ",m.stock_count,m.goods_id,g.goods_name from miaosha_goods m left join goods g "
			+ "on m.goods_id = g.id where m.stock_count>0 and m.id = #{id} ")
	GoodsVo getMiaoshaGoodsById(@Param("id") long id);

	@Select("select m.*,g.goods_name from miaosha_goods m left join goods g on m.goods_id = g.id  where m.id = #{id} ")
	MiaoshaGoods getMiaoGoods(@Param("id") long id);

	@Update("update miaosha_goods set stock_count = stock_count-1 where id = #{id} and stock_count > 0 ")
	int updateMiaoshGoods(@Param("id") long id);
}