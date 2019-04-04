package com.gs.student.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gs.student.dao.StClassMapper;
import com.gs.student.entity.StClass;
import com.gs.student.entity.Student;
import com.gs.student.util.CodeMsg;
import com.gs.student.util.Result;

@Service
public class StClassService {

	@Autowired
	private StClassMapper stClassMapper;

	@Autowired
	private StudentService studentService;

	@Transactional
	public Result<StClass> add(StClass stClass) {
		if (stClass == null) {
			return Result.ERROR(CodeMsg.PARAM_ERROR);
		}
		if (StringUtils.isEmpty(stClass.getClassName())) {
			return Result.ERROR(CodeMsg.CLASSNAME_ERROR);
		}
		if (StringUtils.isEmpty(stClass.getTeacherName())) {
			return Result.ERROR(CodeMsg.TEACHER_NAME_ERROR);
		}
		if (StringUtils.isEmpty(stClass.getTeacherNum())) {
			return Result.ERROR(CodeMsg.TEACHER_NUM_ERROR);
		}
		StClass sts = stClassMapper.selectByName(stClass.getClassName());
		if (sts != null) {
			return Result.ERROR(CodeMsg.CLASS_EXIST);
		}
		stClass.setCreateDate(new Date());
		stClassMapper.insertSelective(stClass);
		return Result.SUCCESS(null);
	}

	@Transactional
	public Result<StClass> update(StClass stClass) {
		StClass cls = stClassMapper.selectByPrimaryKey(stClass.getId());
		if(!stClass.getClassName().equals(cls.getClassName())) {
			StClass sts = stClassMapper.selectByName(stClass.getClassName());
			if (sts != null) {
				return Result.ERROR(CodeMsg.CLASS_EXIST);
			}
		}
		if (StringUtils.isEmpty(stClass.getClassName())) {
			return Result.ERROR(CodeMsg.CLASSNAME_ERROR);
		}
		if (StringUtils.isEmpty(stClass.getTeacherName())) {
			return Result.ERROR(CodeMsg.TEACHER_NAME_ERROR);
		}
		if (StringUtils.isEmpty(stClass.getTeacherNum())) {
			return Result.ERROR(CodeMsg.TEACHER_NUM_ERROR);
		}
		stClass.setUpdateDate(new Date());
		stClassMapper.updateByPrimaryKeySelective(stClass);
		return Result.SUCCESS(null);
	}

	@Transactional
	public Result<StClass> delete(Integer id) {
		List<Student> list = studentService.selectByClassId(id);
		if (list != null && list.size() > 0) {
			return Result.ERROR(CodeMsg.CLASS_EXIST_STUDENT);
		}
		stClassMapper.deleteByPrimaryKey(id);
		return Result.SUCCESS(null);
	}

	public Result<Map<String, Object>> select(StClass stClass) {
		if(stClass.getPage() == null) {
			stClass.setPage(1);
		}
		if(stClass.getPageSize() == null) {
			stClass.setPageSize(10);
		}
		 stClass.setStart((stClass.getPage()-1)*stClass.getPageSize());
		 List<StClass> list = stClassMapper.select(stClass);
		int count = Integer.parseInt(String.valueOf(stClassMapper.selectcount(stClass)));
		Map<String, Object> map =  new HashMap<>();
		int total = count%stClass.getPageSize()>0?(count/stClass.getPageSize()+1):count/stClass.getPageSize();
		map.put("total", total);
		map.put("data", list);
		return Result.SUCCESS(map);
	}

	public Result<StClass> getStClass(Integer id) {
		
		return Result.SUCCESS(stClassMapper.selectByPrimaryKey(id));
	}

	public Result<List<StClass>> getAllClass() {
		return Result.SUCCESS(stClassMapper.getAllClass());
	}

}
