package com.gs.student.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gs.student.entity.User;
import com.gs.student.service.UserService;
import com.gs.student.util.CodeMsg;
import com.gs.student.util.Md5Util;
import com.gs.student.util.Result;
import com.gs.student.util.UUIDUtils;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/toLongin")
	public String toLongin() {
		return "login";
	}

	@RequestMapping("/index")
	public String manager() {
		return "yonghuguanli";
	}

	@RequestMapping("/register")
	public String register() {
		return "register";
	}

	@RequestMapping("/updatePassword")
	public String updatePassword() {
		return "restPassword";
	}

	@RequestMapping("/login")
	@ResponseBody
	public Result<User> doLogin(HttpServletRequest request, HttpServletResponse response, User user) {
		HttpSession session = request.getSession();
		// 1、判断账号密码 是否为空
		if (user == null) {
			return Result.ERROR(CodeMsg.VALIDATE_EXCEPTION, "参数异常！");
		} else if (StringUtils.isEmpty(user.getAccount()) || StringUtils.isEmpty(user.getPassword())) {
			return Result.ERROR(CodeMsg.USERNAME_OR_PASSWORD_IS_NULL);
		}
		// 2、判断用户是否存在
		User u = userService.selectUser(user);

		if (u == null) {
			return Result.ERROR(CodeMsg.USER_NOT_EXIST);
		} else {
			String password = Md5Util.formPassToDBPass(user.getPassword(), u.getSalt());
			if (password.equals(u.getPassword())) {
				session.setAttribute("user", u);
				return Result.SUCCESS(null);
			} else {
				return Result.ERROR(CodeMsg.PASSWORD_ERROR);
			}
		}
	}

	@RequestMapping("/add")
	@ResponseBody
	public Result<User> addUser(User user) {
		// 1、判断账号密码 是否为空'
		if (user == null) {
			return Result.ERROR(CodeMsg.VALIDATE_EXCEPTION, "参数异常！");
		} else if (StringUtils.isEmpty(user.getAccount()) || StringUtils.isEmpty(user.getPassword())) {
			return Result.ERROR(CodeMsg.USERNAME_OR_PASSWORD_IS_NULL);
		}
		// 2、判断用户是否存在
		User u = userService.selectUser(user);
		if (u != null) {
			return Result.ERROR(CodeMsg.USER_EXIST);
		}
		// 3、新增用户
		String salt = UUIDUtils.uuid();
		// md5密码加密
		String password = Md5Util.formPassToDBPass(user.getPassword(), salt);
		user.setPassword(password);
		user.setSalt(salt);
		userService.insert(user);
		return Result.SUCCESS(user);
	}

	@RequestMapping("/update")
	@ResponseBody
	public Result<User> updateUser(User user) {
		// 1、判断账号密码 是否为空'
		if (user == null || user.getId() == null) {
			return Result.ERROR(CodeMsg.VALIDATE_EXCEPTION, "参数异常！");
		}
		User sf = userService.selectById(user.getId());
		
		if(!user.getAccount().equals(sf.getAccount())) {
			User u = userService.selectUser(user);
			// 2、判断用户是否存在
			if (u != null) {
				return Result.ERROR(CodeMsg.ACCOUNT_EXIST);
			}
		}
		if (!StringUtils.isEmpty(user.getPassword()) && user.getPassword().equals(sf.getPassword())) {
			user.setPassword(null);
		}else {
			String password = Md5Util.formPassToDBPass(user.getPassword(), sf.getSalt());
			user.setPassword(password);
		}
		userService.update(user);
		return Result.SUCCESS(user);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Result<Object> deleteUser(Integer id) {
		// 1、判断账号密码 是否为空'
		if (id == null) {
			return Result.ERROR(CodeMsg.VALIDATE_EXCEPTION, "参数异常！");
		}
		// 2、判断用户是否存在
		User u = userService.selectById(id);
		if (u == null) {
			return Result.ERROR(CodeMsg.USER_NOT_EXIST);
		}
		userService.delete(id);
		return Result.SUCCESS(null);
	}

	@RequestMapping("/selectAll")
	@ResponseBody
	public Result<Map<String, Object>> selectAllUser(User user) {
		return userService.selectAllUser(user);
	}

	@RequestMapping("/getUser")
	@ResponseBody
	public Result<User> getUser(Integer id) {
		return Result.SUCCESS(userService.selectById(id));
	}

	@RequestMapping("/restpassword")
	@ResponseBody
	public Result<User> restPassword(User user) {
		return userService.restPassword(user);
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		session.invalidate();
		return "login";
	}

	@RequestMapping("/doregister")
	@ResponseBody
	public Result<User> doregister(User user) {
		// 1、判断账号密码 是否为空'
		if (user == null) {
			return Result.ERROR(CodeMsg.VALIDATE_EXCEPTION, "参数异常！");
		} else if (StringUtils.isEmpty(user.getAccount()) || StringUtils.isEmpty(user.getPassword())) {
			return Result.ERROR(CodeMsg.USERNAME_OR_PASSWORD_IS_NULL);
		} else if (!user.getPassword().equals(user.getCfpassword())) {
			return Result.ERROR(CodeMsg.PASSWORD_CONFORM_ERROR);
		}
		// 2、判断用户是否存在
		User u = userService.selectUser(user);
		if (u != null) {
			return Result.ERROR(CodeMsg.USER_EXIST);
		}
		// 3、新增用户
		String salt = UUIDUtils.uuid();
		// md5密码加密
		String password = Md5Util.formPassToDBPass(user.getPassword(), salt);
		user.setPassword(password);
		user.setSalt(salt);
		user.setRoleId(1);
		userService.insert(user);
		return Result.SUCCESS(user);
	}
}
