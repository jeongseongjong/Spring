package com.biz.friend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.friend.domain.FriendVO;
import com.biz.friend.service.FriendService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class FriendController {

	private final FriendService fService;

	@RequestMapping(value="fList",method=RequestMethod.GET)
	public String selectAll(Model model, FriendVO friendVO, FriendVO fVO){

		List<FriendVO> fList = fService.selectAll();
		log.debug("요거는 친구 리스트얌 : " + fList);
		
		model.addAttribute("FLIST", fList);
		
		return "home";
	}
	
	@RequestMapping(value="/searchName",method=RequestMethod.GET)
	public String search(Model model, @ModelAttribute("search")String f_name, FriendVO friendVO) {
		
		List<FriendVO> nameList = fService.searchName(f_name);
		
		log.debug("요거는 이름검색이야" + nameList.toString());
		model.addAttribute("FLIST", nameList);
		
		
		return "home";
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String insert(Model model, FriendVO friendVO) {
		
		
		
		return "insert";
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(@ModelAttribute FriendVO friendVO, Model model) {
		
		log.debug("인서트 포스트 입니다. " + friendVO.toString());
		
		fService.insert(friendVO);
		
		return "redirect:/fList";
		
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String update(@ModelAttribute FriendVO friendVO, Model model, long f_id) {
		
		friendVO = fService.findById(f_id);
		
		model.addAttribute("friendVO", friendVO);
		
		return "insert";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(@ModelAttribute("friendVO") FriendVO friendVO) {
		
		fService.update(friendVO);
		
		return "redirect:/fList";
		
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(@ModelAttribute("f_id")long f_id) {
		
		fService.delete(f_id);
		
		return "redirect:/fList";
	}
}
