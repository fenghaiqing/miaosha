package com.gs.miaosha.service;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.gs.miaosha.entity.User;
import com.gs.miaosha.result.Result;
import com.gs.miaosha.vo.UserVo;

public interface UserService {

	public Result<User> getUser(String string);

	public Result<UserVo> doLogin(HttpServletResponse response, @Valid UserVo user);

	public User getUserByToken(String token, HttpServletResponse response);
}
