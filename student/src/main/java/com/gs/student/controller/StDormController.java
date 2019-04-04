package com.gs.student.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gs.student.entity.StDorm;
import com.gs.student.service.StDormService;
import com.gs.student.util.Result;

@Controller
@RequestMapping("/dorm")
public class StDormController {

	@Autowired
	private StDormService stDormService;
	
	@RequestMapping("/index")
	public String index(){
		return "susheguanli";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Result<StDorm> add(StDorm stDorm){
		return stDormService.add(stDorm);
	}
	@RequestMapping("/delete")
	@ResponseBody
	public Result<StDorm> delete(Integer id){
		return stDormService.delete(id);
	}
	@RequestMapping("/update")
	@ResponseBody
	public Result<StDorm> update(StDorm stDorm){
		return stDormService.update(stDorm);
	}
	
	@RequestMapping("/selset")
	@ResponseBody
	public Result<Map<String, Object>> selset(StDorm stDorm){
		return stDormService.selset(stDorm);
	}
	
	@RequestMapping("/getStDorm")
	@ResponseBody
	public Result<StDorm> getStDorm(Integer id){
		return stDormService.getStDorm(id);
	}
	
	@RequestMapping("/getStDormBySex")
	@ResponseBody
	public Result<List<StDorm>> getStDormBySex(String sex){
		return stDormService.getStDormBySex(sex);
	}
	
}
