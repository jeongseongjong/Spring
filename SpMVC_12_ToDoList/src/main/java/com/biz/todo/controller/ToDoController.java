package com.biz.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.todo.domain.ToDoList;
import com.biz.todo.service.ToDoService;

@Controller
public class ToDoController {

	// ToDoService1 / 2에서 service 어노테이션으로 설정한 ("toService2")부분을 가져와
	// toService로 사용하겠다. -> 
	@Autowired
	@Qualifier("toServiceV2")
	ToDoService toService;
	
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String list(Model model) {
		/*
		 * 팀프로젝트에서 Controller 개발자와, Service 개발자가 다를경우
		 * Service 는 interface가 정의 되어 있기 때문에
		 * 아직 기능은 구현되지 않았어도
		 * Controller 개발자는 당연히 Service의 method를 호출하면
		 * 결과가 리턴될것이다.
		 */
		List<ToDoList> toDoList = toService.selectAll();
		
		model.addAttribute("todoList", toDoList);
		
		return "home";
	}
	
	@RequestMapping(value="list", method=RequestMethod.POST)
	public String insert(@ModelAttribute ToDoList toDoList, Model model) {
		
		int ret = toService.insert(toDoList);
		
		if(ret < 1) {
			model.addAttribute("INSERT_ERROR", "NOT_INSERT");
		}
		
		return "redirect:/list";
	}
	
	@RequestMapping(value="complete", method=RequestMethod.GET)
	public String complete(@RequestParam("tdSeq")String strSeq,
							@RequestParam("tdCoplete") String tdComplete) {
		
		toService.complete(strSeq,tdComplete);
		
		
		return "redirect:/list";
	}
	
	@RequestMapping(value="alarm", method=RequestMethod.GET)
	public String alarm(@RequestParam("tdSeq")String strSeq,
							@RequestParam("tdAlarm") String tdAlarm) {
		
		toService.alarm(strSeq,tdAlarm);
		
		
		return "redirect:/list";
	}
}
