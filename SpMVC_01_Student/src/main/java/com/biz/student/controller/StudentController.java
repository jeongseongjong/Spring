package com.biz.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.student.domain.StudentDTO;
import com.biz.student.service.StudentService;

@Controller
// model 이란 DTO,DAO,LIST등의 자료형을 담을 수 있는 객체
public class StudentController {

	@Autowired
	StudentService sService ;
	
//	public StudentController() {
//		sService = new StudentService();
//	}
	
	@RequestMapping(value="input",method=RequestMethod.GET)
	public String input() {
		return "student/input";
	}
	
	@RequestMapping(value="search",method=RequestMethod.GET)
	public String search(StudentDTO dto) {
		return " student/search";
	}
	
	@RequestMapping(value="view",method=RequestMethod.GET)
	public String view() {
		return "student/view";
	}
	
	@RequestMapping(value = "input", method = RequestMethod.POST)
	public String input(StudentDTO dto, Model model) {
		System.out.println(dto.toString());
		model.addAttribute("dto",dto);
		return "student/view";
	}
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String view(Model model) {
		List<StudentDTO> stdList = sService.stdList();
		model.addAttribute("stdList", stdList);
		for(StudentDTO dto : stdList) {
			dto.getSt_num();
			dto.getSt_name();
			dto.getSt_dept();
			dto.getSt_grade();
			dto.getSt_tel();
		System.out.println(dto);
		
		}
		return "student/viewList";
	}
}
