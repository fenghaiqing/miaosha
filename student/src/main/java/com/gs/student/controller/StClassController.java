package com.gs.student.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gs.student.entity.StClass;
import com.gs.student.service.StClassService;
import com.gs.student.util.Result;

@Controller
@RequestMapping("/class")
public class StClassController {

	@Autowired
	private StClassService stClassService;
	
	@RequestMapping("/index")
	public String index(){
		return "banjiguanli";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Result<StClass> add(StClass stClass){
		return stClassService.add(stClass);
	}
	@RequestMapping("/update")
	@ResponseBody
	public Result<StClass> update(StClass stClass){
		return stClassService.update(stClass);
	}
	@RequestMapping("/delete")
	@ResponseBody
	public Result<StClass> delete(Integer id){
		return stClassService.delete(id);
	}
	@RequestMapping("/select")
	@ResponseBody
	public Result<Map<String, Object>> select(StClass stClass){
		return stClassService.select(stClass);
	}
	@RequestMapping("/getStClass")
	@ResponseBody
	public Result<StClass> getStClass(Integer id){
		return stClassService.getStClass(id);
	}
	
	@RequestMapping("/getAllClass")
	@ResponseBody
	public Result<List<StClass>> getAllClass(){
		return stClassService.getAllClass();
	}
}
