package com.gs.student.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gs.student.dao.StDormMapper;
import com.gs.student.dao.StudentMapper;
import com.gs.student.entity.StDorm;
import com.gs.student.entity.Student;
import com.gs.student.util.CodeMsg;
import com.gs.student.util.Result;

@Service
public class StudentService {

	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private StDormMapper stDormMapper;
	
	@Transactional
	public Result<Student> add(Student student) {
		// 1、判断必填项
		if(student == null) {
			return Result.ERROR(CodeMsg.PARAM_ERROR);
		}
		if(StringUtils.isEmpty(student.getStdNumber())) {
			return Result.ERROR(CodeMsg.STD_NUMBER_ERROR);
		}
		if(null == student.getClassId()) {
			return Result.ERROR(CodeMsg.STD_ClASS_ERROR);
		}
		if(null == student.getDormId()) {
			return Result.ERROR(CodeMsg.STD_DORM_ERROR);
		}
		if(StringUtils.isEmpty(student.getName())) {
			return Result.ERROR(CodeMsg.STD_NAME_ERROR);
		}
		if(StringUtils.isEmpty(student.getSex())) {
			return Result.ERROR(CodeMsg.STD_SEX_ERROR);
		}
		// 2、判断学生是否存在
		Student std = studentMapper.selectByStdNumber(student.getStdNumber());
		if(std !=null) {
			return Result.ERROR(CodeMsg.STD_EXIST);
		}
		// 3、判断宿舍是否有空床位
		StDorm dorm = stDormMapper.selectByPrimaryKey(student.getDormId());
		if(dorm == null) {
			return Result.ERROR(CodeMsg.DORM_IS_NOT_EXIST);
		}
		if(dorm.getMaxNum()>dorm.getSurplusNum()) { //有空余床位
			// 4、新增
			student.setCreateDate(new Date());
			studentMapper.insertSelective(student);
			StDorm updorm = new StDorm();
			updorm.setId(dorm.getId());
			updorm.setSurplusNum(dorm.getSurplusNum()+1);
			//更新宿舍床位信息
			stDormMapper.updateByPrimaryKeySelective(updorm);
			return Result.SUCCESS(student);
		}else {
			return Result.ERROR(CodeMsg.DORM_IS_FUll);
		}
		
	}

	@Transactional
	public Result<Student> delete(Integer id) {
		Student std = studentMapper.selectByPrimaryKey(id);
		StDorm dorm = stDormMapper.selectByPrimaryKey(std.getDormId());
		StDorm updorm = new StDorm();
		updorm.setId(dorm.getId());
		updorm.setSurplusNum(dorm.getSurplusNum()-1);
		stDormMapper.updateByPrimaryKeySelective(updorm);
		studentMapper.deleteByPrimaryKey(id);
		return Result.SUCCESS(null);
	}

	@Transactional
	public Result<Student> update(Student student) {
		if(StringUtils.isEmpty(student.getStdNumber())) {
			return Result.ERROR(CodeMsg.STD_NUMBER_ERROR);
		}
		if(null == student.getClassId()) {
			return Result.ERROR(CodeMsg.STD_ClASS_ERROR);
		}
		if(null == student.getDormId()) {
			return Result.ERROR(CodeMsg.STD_DORM_ERROR);
		}
		if(StringUtils.isEmpty(student.getName())) {
			return Result.ERROR(CodeMsg.STD_NAME_ERROR);
		}
		if(StringUtils.isEmpty(student.getSex())) {
			return Result.ERROR(CodeMsg.STD_SEX_ERROR);
		}
		Student s = studentMapper.selectByPrimaryKey(student.getId());
		if(!s.getStdNumber().equals(student.getStdNumber())) {
			// 2、判断学生是否存在
			Student std = studentMapper.selectByStdNumber(student.getStdNumber());
			if(std !=null) {
				return Result.ERROR(CodeMsg.STD_EXIST);
			}
		}
		if(s.getDormId() != student.getDormId()) {
			// 3、判断宿舍是否有空床位
			StDorm dorm = stDormMapper.selectByPrimaryKey(student.getDormId());
			if(dorm.getMaxNum()>dorm.getSurplusNum()) {
				StDorm updorm = new StDorm();
				updorm.setId(dorm.getId());
				updorm.setSurplusNum(dorm.getSurplusNum()+1);
				//更新宿舍床位信息
				updorm.setUpdateDate(new Date());
				stDormMapper.updateByPrimaryKeySelective(updorm);
			}else {
				return Result.ERROR(CodeMsg.DORM_IS_FUll);
			}
		}
		student.setUpdateDate(new Date());
		studentMapper.updateByPrimaryKeySelective(student);
		return Result.SUCCESS(null);
	}

	
	public Result<Map<String, Object>> query(Student student) {
		if(student.getPage() == null) {
			student.setPage(1);
		}
		if(student.getPageSize() == null) {
			student.setPageSize(10);
		}
		if(StringUtils.isEmpty(student.getName())) {
			student.setName(null);
		}
		if(StringUtils.isEmpty(student.getSex())) {
			student.setSex(null);
		}
		student.setStart((student.getPage()-1)*student.getPageSize());
		List<Student> list = studentMapper.query(student);
		int count = Integer.parseInt(String.valueOf(studentMapper.selectCount(student)));
		Map<String, Object> map =  new HashMap<>();
		int total = count%student.getPageSize()>0?(count/student.getPageSize()+1):count/student.getPageSize();
		map.put("total", total);
		map.put("data", list);
		return Result.SUCCESS(map);
	}

	public Result<Student> getStudent(Integer dormId) {
		Student std = studentMapper.selectByPrimaryKey(dormId);
		return Result.SUCCESS(std);
	}

	public List<Student> selectByDormId(Integer id) {
		return studentMapper.selectByDormId(id);
	}

	public List<Student> selectByClassId(Integer classId) {
		return studentMapper.selectByClassId(classId);
	}

}
