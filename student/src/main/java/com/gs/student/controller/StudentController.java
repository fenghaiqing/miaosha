package com.gs.student.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gs.student.entity.Student;
import com.gs.student.service.StudentService;
import com.gs.student.util.Result;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/index")
	public String index(Student student){
		return "index";
	}
	
	/**
	 * 新增学生信息
	 * @param student
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Result<Student> add(Student student){
		return studentService.add(student);
	}
	
	/**
	 * 删除学生信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result<Student> delete(Integer id){
		return studentService.delete(id);
	}
	
	/**
	 * 修改学生信息
	 * @param student
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Result<Student> update(Student student){
		return studentService.update(student);
	}
	/**
	 * 分页查询用户信息
	 * @param student
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Result<Map<String, Object>> query(Student student){
		return studentService.query(student);
	}
	
	/**
	 * 根据id获取学生信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/getStudent")
	@ResponseBody
	public Result<Student> getStudent(Integer id){
		return studentService.getStudent(id);
	}
}
