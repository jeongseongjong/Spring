package com.biz.rbooks.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.rbooks.domain.MemberDTO;
import com.biz.rbooks.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member")
public class MemberController {

	@Autowired
	UserService uService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(@RequestParam(value="LOGIN_MSG", required = false, defaultValue="0")String msg, Model model) {
		model.addAttribute("LOGIN_MSG", msg);
		model.addAttribute("BODY", "LOGIN");
		
		return "member/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(String BODY, @ModelAttribute MemberDTO memberDTO, Model model, HttpSession hSession) {
		
		boolean loginOK = uService.userLoginCheck(memberDTO);
		
		if(loginOK == true) {
			hSession.setMaxInactiveInterval(10*600);
			memberDTO = uService.getUser(memberDTO.getM_id());
			hSession.setAttribute("memberDTO", memberDTO);
		}else {
			hSession.removeAttribute("memberDTO");
			model.addAttribute("LOGIN_MSG", "FAIL");
			
			return "redirect:/member/login";
		}
		
		model.addAttribute("BODY", BODY);
		return "redirect:/";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(Model model) {
		
		MemberDTO memberDTO = new MemberDTO();
		
		model.addAttribute("memberDTO", memberDTO);
		
		return "member/join";
		
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute("memberDTO") @Valid MemberDTO memberDTO, BindingResult bResult, Model model) {
		
		if(bResult.hasErrors()) {
			return "member/join";
		}else {
			int ret = uService.userJoin(memberDTO);
			
			return "redirect:/";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/idcheck", method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public boolean userIdCheck(@RequestParam(value="m_id", required = false, defaultValue = "0")String m_id) {
		
		boolean idYes = uService.userIdCheck(m_id);
		log.debug("아이디 중복 검사 : " + idYes);
		
		return idYes;
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession httpSession) {
		
		httpSession.setAttribute("memberDTO", null);
		httpSession.removeAttribute("memberDTO");
		
		return "redirect:/";
	}
//	@Autowired
//	BCryptPasswordEncoder passwordEncoder;
//	@ResponseBody
//	@RequestMapping(value="/pass", method=RequestMethod.GET)
//	public String passwordTest(@RequestParam(value="strText",required=false, defaultValue="KOREA")String strText) {
//		String cryptTest = passwordEncoder.encode(strText);
//		long textLeng = cryptTest.length();
//		
//		return cryptTest + ":" + textLeng;
//	}
	
}
