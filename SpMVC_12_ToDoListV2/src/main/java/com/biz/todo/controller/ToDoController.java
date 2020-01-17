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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ToDoController {

	// ToDoService1 / 2에서 service 어노테이션으로 설정한 ("toService2")부분을 가져와
	// toService로 사용하겠다. -> 
	@Autowired
	@Qualifier("todoV3")
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
	public String complete(@RequestParam("td_seq")String str_seq,
							@RequestParam("td_complete") String td_complete) {
		
		toService.complete(str_seq,td_complete);
		
		return "redirect:/list";
	}
	
	@RequestMapping(value="alarm", method=RequestMethod.GET)
	public String alarm(@RequestParam("td_seq")String str_seq,
						@RequestParam("td_alarm") String td_alarm) {
		
		toService.alarm(str_seq,td_alarm);
		
		
		return "redirect:/list";
	}
	
	@RequestMapping(value="delete",method=RequestMethod.GET)
	public String delete(@RequestParam("td_seq")String str_seq) {
		
		try {
			
			long td_seq = Long.valueOf(str_seq);	
			toService.delete(td_seq);
			
		} catch (Exception e) {
			log.debug("삭제 오류");
		
		}
		
		
		
		
		return "redirect:/list";
	}
	
	@RequestMapping(value="update",method=RequestMethod.GET)
	public String update(@RequestParam("td_seq")String str_seq, Model model) {
		
		long td_seq = Long.valueOf(str_seq);
		
		ToDoList toDTO = toService.findBySeq(td_seq);
		model.addAttribute("todoDTO", toDTO);
		
		List<ToDoList> tdList = toService.selectAll();
		model.addAttribute("todoList", tdList);
		
		return "home";
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String update(ToDoList toList, Model model) {
		
		toService.update(toList);
		
		return "redirect:/list";
	}
}




















