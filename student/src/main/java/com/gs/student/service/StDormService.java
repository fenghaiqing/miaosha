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
import com.gs.student.entity.StDorm;
import com.gs.student.entity.Student;
import com.gs.student.util.CodeMsg;
import com.gs.student.util.Result;

@Service
public class StDormService {

	@Autowired
	private StDormMapper stDormMapper;

	@Autowired
	private StudentService studentService;

	@Transactional
	public Result<StDorm> add(StDorm stDorm) {
		if (stDorm == null) {
			return Result.ERROR(CodeMsg.PARAM_ERROR);
		}
		if (StringUtils.isEmpty(stDorm.getName())) {
			return Result.ERROR(CodeMsg.DORM_NAME_ERROR);
		}
		if (StringUtils.isEmpty(stDorm.getSex())) {
			return Result.ERROR(CodeMsg.DORM_SEX_ERROR);
		}
		StDorm dm = stDormMapper.selectByName(stDorm.getName());
		if (dm != null) {
			return Result.ERROR(CodeMsg.DORM_EXIST);
		}
		stDorm.setCreateDate(new Date());
		stDorm.setSurplusNum(0);
		stDormMapper.insertSelective(stDorm);
		return Result.SUCCESS(null);
	}

	@Transactional
	public Result<StDorm> delete(Integer id) {
		List<Student> list = studentService.selectByDormId(id);
		if (list != null && list.size() > 0) {
			return Result.ERROR(CodeMsg.DORM_EXIST_STUDENT);
		}
		stDormMapper.deleteByPrimaryKey(id);
		return Result.SUCCESS(null);
	}

	@Transactional
	public Result<StDorm> update(StDorm stDorm) {
		if (stDorm == null || stDorm.getId() == null) {
			return Result.ERROR(CodeMsg.PARAM_ERROR);
		}
		if (StringUtils.isEmpty(stDorm.getName())) {
			return Result.ERROR(CodeMsg.DORM_NAME_ERROR);
		}
		if (StringUtils.isEmpty(stDorm.getSex())) {
			return Result.ERROR(CodeMsg.DORM_SEX_ERROR);
		}
		if (stDorm.getMaxNum()==null) {
			stDorm.setMaxNum(0);
		}
		StDorm dm = stDormMapper.selectByPrimaryKey(stDorm.getId());
		if(!stDorm.getName().equals(dm.getName())) {
			StDorm sdm = stDormMapper.selectByName(stDorm.getName());
			if (sdm != null) {
				return Result.ERROR(CodeMsg.DORM_EXIST);
			}
		}
		if(stDorm.getMaxNum()<dm.getSurplusNum()) {
			return Result.ERROR(CodeMsg.PARAM_ERROR);
		}
		stDorm.setUpdateDate(new Date());
		stDormMapper.updateByPrimaryKeySelective(stDorm);
		return Result.SUCCESS(null);
	}

	public Result<Map<String, Object>> selset(StDorm stDorm) {
		
		if(stDorm.getPage() == null) {
			stDorm.setPage(1);
		}
		if(stDorm.getPageSize() == null) {
			stDorm.setPageSize(10);
		}
		stDorm.setStart((stDorm.getPage()-1)*stDorm.getPageSize());
		List<StDorm> list = stDormMapper.select(stDorm);
		int count = Integer.parseInt(String.valueOf(stDormMapper.selectcount(stDorm)));
		Map<String, Object> map =  new HashMap<>();
		int total = count%stDorm.getPageSize()>0?(count/stDorm.getPageSize()+1):count/stDorm.getPageSize();
		map.put("total", total);
		map.put("data", list);
		return Result.SUCCESS(map);
	}

	public Result<StDorm> getStDorm(Integer id) {
		return Result.SUCCESS(stDormMapper.selectByPrimaryKey(id));
	}

	public Result<List<StDorm>> getStDormBySex(String sex) {
		return Result.SUCCESS(stDormMapper.getStDormBySex(sex));
	}

}
