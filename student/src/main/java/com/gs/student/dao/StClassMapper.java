package com.gs.student.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.gs.student.entity.StClass;
import com.gs.student.util.Result;
@Mapper
public interface StClassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StClass record);

    int insertSelective(StClass record);

    StClass selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StClass record);

    int updateByPrimaryKey(StClass record);

    @Select("select * from st_class where CLASS_NAME =#{className}")
	StClass selectByName(@Param("className")String className);

	List<StClass> select(StClass stClass);

	List<StClass> getAllClass();

	long selectcount(StClass stClass);
}