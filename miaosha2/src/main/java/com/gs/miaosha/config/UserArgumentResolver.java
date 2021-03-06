package com.gs.miaosha.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.gs.miaosha.entity.User;
import com.gs.miaosha.service.UserService;
import com.gs.miaosha.service.impl.UserServiceImpl;

@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

	@Autowired
	private UserService userService;
	
	@Override
	public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest,
			WebDataBinderFactory webDataBinderFactory) throws Exception {
		HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
		HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
		String paramToken = request.getParameter(UserServiceImpl.COOKIE_NAME_TOKEN);
		String cookieToken =getCookieValue(request,UserServiceImpl.COOKIE_NAME_TOKEN);
		
		String token = StringUtils.isEmpty(cookieToken)?paramToken:cookieToken;
		return userService.getUserByToken(token,response);
	}

	private String getCookieValue(HttpServletRequest request,String cookieNameToken) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals(cookieNameToken)) {
				return cookie.getValue();
			}
		}
		return null;
	}

	@Override
	public boolean supportsParameter(MethodParameter paramater) {
		Class<?> clazz =paramater.getParameterType();
		return clazz == User.class;
	}

}
