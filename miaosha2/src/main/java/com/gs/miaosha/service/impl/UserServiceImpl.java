package com.gs.miaosha.service.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gs.miaosha.dao.UserDao;
import com.gs.miaosha.entity.User;
import com.gs.miaosha.exception.GlobalException;
import com.gs.miaosha.redis.RedisService;
import com.gs.miaosha.redis.UserKey;
import com.gs.miaosha.result.CodeMsg;
import com.gs.miaosha.result.Result;
import com.gs.miaosha.service.UserService;
import com.gs.miaosha.util.Md5Util;
import com.gs.miaosha.util.UUIDUtils;
import com.gs.miaosha.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {

	//cookie名称
	public static final String COOKIE_NAME_TOKEN="token";
	//cookie过期时间
	public static final int COKKIE_EXPER_TIMES = 300;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RedisService redis;
	
	@Override
	public Result<User> getUser(String id) {
		return Result.SUCCESS(userDao.getUser(id));
	}
	
	@Override
	public Result<UserVo> doLogin(HttpServletResponse response, @Valid UserVo user) {
		User loginuser = userDao.getUser(user.getId());
		if(loginuser == null) {
			throw new GlobalException(CodeMsg.USER_NOT_EXIST);
		}
		String dbpass = Md5Util.formPassToDBPass(user.getPassword(), Md5Util.salt);
		if(loginuser.getPassword().equals(dbpass)) {
			addCookie(loginuser,response);
			return Result.SUCCESS(user);
		}else {
			throw new GlobalException(CodeMsg.PASSWORD_ERROR);
		}
	}

	@Override
	public User getUserByToken(String token, HttpServletResponse response) {
		if(StringUtils.isEmpty(token)) {
			return null;
		}else {
			User user =redis.get(UserKey.getKey(COOKIE_NAME_TOKEN,token),User.class);
			if(user!=null) {
				addCookie(user,response);
			}
			return user;
		}
	}

	private void addCookie(User loginuser,HttpServletResponse response) {
		String tk = UUIDUtils.uuid();
		redis.set(UserKey.getKey(COKKIE_EXPER_TIMES,COOKIE_NAME_TOKEN,tk), loginuser);
		Cookie cookie =new Cookie(COOKIE_NAME_TOKEN,tk);
		cookie.setMaxAge(COKKIE_EXPER_TIMES);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
}
