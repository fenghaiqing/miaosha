package com.gs.student.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gs.student.dao.UserMapper;
import com.gs.student.entity.User;
import com.gs.student.util.CodeMsg;
import com.gs.student.util.Md5Util;
import com.gs.student.util.Result;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public User selectUser(User user) {

		return userMapper.selectUser(user);
	}

	@Transactional
	public boolean insert(User user) {
		return userMapper.insertSelective(user) > 0 ? true : false;
	}

	@Transactional
	public void update(User user) {
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Transactional
	public void delete(Integer id) {
		userMapper.deleteByPrimaryKey(id);

	}

	public User selectById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	public Result<Map<String,Object>> selectAllUser(User user) {
		if(user.getPage() == null) {
			user.setPage(1);
		}
		if(user.getPageSize() == null) {
			user.setPageSize(10);
		}
		user.setStart((user.getPage()-1)*user.getPageSize());
		List<User> list = userMapper.selectAllUser(user);
		int count = Integer.parseInt(String.valueOf(userMapper.selectcount(user)));
		Map<String, Object> map =  new HashMap<>();
		int total = count%user.getPageSize()>0?(count/user.getPageSize()+1):count/user.getPageSize();
		map.put("total", total);
		map.put("data", list);
		return Result.SUCCESS(map);
	}

	public Result<User> restPassword(User user) {
		if (StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getNewpassword())
				|| StringUtils.isEmpty(user.getCfpassword()) || user.getId() ==null) {
			return Result.ERROR(CodeMsg.PARAM_ERROR);
		}
		User u = userMapper.selectByPrimaryKey(user.getId());
		String inputPassword = Md5Util.formPassToDBPass(user.getPassword(), u.getSalt());
		if (!u.getPassword().equals(inputPassword)) {
			return Result.ERROR(CodeMsg.OLD_PASSWORD_ERROR);
		}
		if (!user.getNewpassword().equals(user.getCfpassword())) {
			return Result.ERROR(CodeMsg.PASSWORD_CONFORM_ERROR);
		}
		String newpassword = Md5Util.formPassToDBPass(user.getNewpassword(), u.getSalt());
		if (inputPassword.equals(newpassword)) {
			return Result.ERROR(CodeMsg.PASSWORD_SAM_ERROR);
		}
		user.setPassword(newpassword);
		userMapper.updateByPrimaryKeySelective(user);
		return Result.SUCCESS(null);
	}

}
