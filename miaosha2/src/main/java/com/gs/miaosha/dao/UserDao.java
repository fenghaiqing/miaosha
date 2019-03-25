package com.gs.miaosha.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.gs.miaosha.entity.User;


@Mapper
public interface UserDao {
	@Select("SELECT *FROM USER where id = #{id}")
	public User getUser(@Param("id") String id);
}
