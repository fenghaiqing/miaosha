package com.gs.student.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class SecurityFilter extends HandlerInterceptorAdapter {

	/**
	 * �����¼��url
	 */
	private String[] exclude_paths;
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestURI = request.getRequestURI();
		boolean needLogin = true;
		AntPathMatcher matcher = new AntPathMatcher();
		for (String noAuthUrl : exclude_paths) {
			if (requestURI.indexOf(noAuthUrl) != -1) {
				// �����ж��Ƿ��¼
				return super.preHandle(request, response, handler);
			}
		}
		if (needLogin) {
			// ��֤�Ƿ��¼
			Object object = request.getSession().getAttribute("user");
			if(object == null) {
				response.sendRedirect(request.getContextPath() + "/user/toLongin");
				return false;
			}
		}
		return super.preHandle(request, response, handler);
	}

	public String[] getExclude_paths() {
		return exclude_paths;
	}

	public void setExclude_paths(String[] exclude_paths) {
		this.exclude_paths = exclude_paths;
	}
}
