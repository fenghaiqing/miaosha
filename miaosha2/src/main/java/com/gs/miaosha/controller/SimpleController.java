package com.gs.miaosha.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gs.miaosha.entity.User;
import com.gs.miaosha.rabbitmq.MQSender;
import com.gs.miaosha.redis.RedisService;
import com.gs.miaosha.redis.UserKey;
import com.gs.miaosha.result.CodeMsg;
import com.gs.miaosha.result.Result;
import com.gs.miaosha.service.UserService;

@Controller
public class SimpleController {

	@Autowired
	private RedisService redisService;
	@Autowired
	private UserService userService;
	@Autowired
	private MQSender send;
	
	@RequestMapping("/hello")
	public String hello(Model mod) {
		mod.addAttribute("name", "张三");
		return "hello";
	}
	
	@RequestMapping("/user")
	@ResponseBody
	public Result<Map<String ,Object>> getUser(){
		Map<String ,Object> map = new HashMap<>();
		map.put("name", "张三");
		map.put("id", 1);
		return Result.SUCCESS(map);
	}
	@RequestMapping("/err")
	@ResponseBody
	public Result<Map<String ,Object>> error(){
		
		return Result.ERROR(CodeMsg.ERROR_O1);
	}
	
	@RequestMapping("/redis/set")
	@ResponseBody
	public Result<Boolean> setvalue() {
		 redisService.set(UserKey.getKey("user", "1"), "aaaaa");
		return Result.SUCCESS(true);
	}
	
	@RequestMapping("/getUser")
	@ResponseBody
	public Result<User> getUser(String id) {
		
		return userService.getUser(id);
	}
	
	@RequestMapping("/rabbit")
	@ResponseBody
	public Result<Map> rabbit() {
		Map<String ,Object> map = new HashMap<>();
		map.put("name", "张三");
		map.put("id", 1);
		send.send(map);
		return Result.SUCCESS(null);
	}
	
	@RequestMapping("/topic")
	@ResponseBody
	public Result<Map> topic() {
		Map<String ,Object> map = new HashMap<>();
		map.put("name", "张三");
		map.put("id", 1);
		send.topicSend(map);
		return Result.SUCCESS(null);
	}
	
	@RequestMapping("/fanout")
	@ResponseBody
	public Result<Map> fanout() {
		Map<String ,Object> map = new HashMap<>();
		map.put("name", "张三");
		map.put("id", 1);
		send.fanoutSend(map);
		return Result.SUCCESS(null);
	}
}
