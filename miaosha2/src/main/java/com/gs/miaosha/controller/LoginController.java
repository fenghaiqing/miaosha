package com.gs.miaosha.controller;


import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gs.miaosha.result.Result;
import com.gs.miaosha.service.UserService;
import com.gs.miaosha.vo.UserVo;

@Controller
@RequestMapping("/login")
public class LoginController {

	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "login";
	}
	
	@RequestMapping("/doLogin")
	@ResponseBody
	public Result<UserVo> doLogin(HttpServletResponse response,@Valid UserVo user) {
			return userService.doLogin(response,user);
	}
}
