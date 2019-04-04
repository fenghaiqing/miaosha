package com.gs.student.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.gs.student.entity.Student;

@Mapper
public interface StudentMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Student record);

	int insertSelective(Student record);

	Student selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Student record);

	int updateByPrimaryKey(Student record);

	@Select("select * from student where std_number =#{stdNumber}  ")
	Student selectByStdNumber(@Param("stdNumber") String stdNumber);

	List<Student> query(Student student);

	Long selectCount(Student student);

	@Select("select * from student where DORM_ID =#{dormId}  ")
	List<Student> selectByDormId(@Param("dormId") Integer dormId);

	@Select("select * from student where CLASS_ID =#{classId}  ")
	List<Student> selectByClassId(@Param("classId") Integer classId);
}