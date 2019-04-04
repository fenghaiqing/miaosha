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
	 * ����ѧ����Ϣ
	 * @param student
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Result<Student> add(Student student){
		return studentService.add(student);
	}
	
	/**
	 * ɾ��ѧ����Ϣ
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result<Student> delete(Integer id){
		return studentService.delete(id);
	}
	
	/**
	 * �޸�ѧ����Ϣ
	 * @param student
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Result<Student> update(Student student){
		return studentService.update(student);
	}
	/**
	 * ��ҳ��ѯ�û���Ϣ
	 * @param student
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Result<Map<String, Object>> query(Student student){
		return studentService.query(student);
	}
	
	/**
	 * ����id��ȡѧ����Ϣ
	 * @param id
	 * @return
	 */
	@RequestMapping("/getStudent")
	@ResponseBody
	public Result<Student> getStudent(Integer id){
		return studentService.getStudent(id);
	}
}
