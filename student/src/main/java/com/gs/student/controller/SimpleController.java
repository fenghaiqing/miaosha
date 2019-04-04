package com.gs.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gs.student.dao.UserMapper;
import com.gs.student.entity.User;
import com.gs.student.service.UserService;
import com.gs.student.util.Result;

@Controller
public class SimpleController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping("hello")
	@ResponseBody
	public String hello() {
		return "hello";
	}
	
	@RequestMapping("/select")
	@ResponseBody
	public Result<User> selectUser() {
		User user = new User();
		user.setAccount("001");
		user.setPassword("12345");
		// 3、新增用户
		User u =  userMapper.selectUser(user);
		return Result.SUCCESS(u);
	}
}
