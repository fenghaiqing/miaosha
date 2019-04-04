package com.gs.student.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.gs.student.entity.StDorm;

@Mapper
public interface StDormMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StDorm record);

    int insertSelective(StDorm record);

    StDorm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StDorm record);

    int updateByPrimaryKey(StDorm record);

    @Select("select * from ST_DORM where name = #{name}")
	StDorm selectByName(String name);

	List<StDorm> select(StDorm stDorm);

	 @Select("select * from ST_DORM where sex = #{sex} and max_Num > surplus_Num")
	 List<StDorm> getStDormBySex(String sex);

	long selectcount(StDorm stDorm);
}