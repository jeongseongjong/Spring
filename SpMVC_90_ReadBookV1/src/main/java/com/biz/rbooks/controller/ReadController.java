package com.biz.rbooks.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.rbooks.domain.MemberDTO;
import com.biz.rbooks.domain.ReadDTO;
import com.biz.rbooks.repository.ReadDao;
import com.biz.rbooks.service.ReadService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(value = "/read")
public class ReadController {

	@Autowired
	ReadService rService;
	@Autowired
	ReadDao rDao;

//	@RequestMapping(value="rList",method=RequestMethod.GET)
//	public String getRlist(Model model, @ModelAttribute ReadDTO readDTO, HttpSession hSession) {
//		
////		MemberDTO memberDTO = (MemberDTO) hSession.getAttribute("memberDTO");
//		
////		if(memberDTO == null) {
////			model.addAttribute("BODY", "LOGIN");
////			model.addAttribute("LOGIN_MSG", "TRY");
////			return "redirect:/member/login";
////		}
//		
//		List<ReadDTO> readList = rService.selectAll();
//		model.addAttribute("HOME", "READ_INSERT");
//		model.addAttribute("RLIST", readList);
//		
//		log.debug("여기는 리드 디티오 : " + readDTO);
//		log.debug("독서 리스트" + readList.toString());
//		
//		return "template";
//	}

	@RequestMapping(value = "/view-seq", method = RequestMethod.GET)
	public String view(@RequestParam("id") long rb_seq, Model model, HttpSession hSession) {

//		MemberDTO memberDTO = (MemberDTO) hSession.getAttribute("memberDTO");

//		if (memberDTO == null) {
//			model.addAttribute("BODY", "LOGIN");
//			model.addAttribute("LOGIN_MSG", "TRY");
//			return "redirect:/member/login";
//		}

		ReadDTO readDTO = rService.findByRbSep(rb_seq);

		model.addAttribute("readDTO", readDTO);
		model.addAttribute("HOME", "RLIST_DETAIL");

		return "template";
	}

	@RequestMapping(value = "/view-code", method = RequestMethod.GET)
	public String vieww(@RequestParam("id") String rb_bcode, Model model, HttpSession hSession) {

//		MemberDTO memberDTO = (MemberDTO) hSession.getAttribute("memberDTO");
//
//		if (memberDTO == null) {
//			model.addAttribute("BODY", "LOGIN");
//			model.addAttribute("LOGIN_MSG", "TRY");
//			return "redirect:/member/login";
//		}

		log.debug("rbcode가 온것이냐 만것이냐 : " + rb_bcode);
		List<ReadDTO> readList = rDao.findByRBCode(rb_bcode);
		log.debug("rbcode가 온것이냐 만것이냐2 : " + readList.toString());
		model.addAttribute("readList", readList);
		model.addAttribute("HOME", "RLIST");

		return "template";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(@ModelAttribute("readDTO") ReadDTO readDTO, Model model, HttpSession hSession) {

		MemberDTO memberDTO = (MemberDTO) hSession.getAttribute("memberDTO");

		if (memberDTO == null) {
			model.addAttribute("BODY", "LOGIN");
			model.addAttribute("LOGIN_MSG", "TRY");
			return "redirect:/member/login";
		}

		log.debug("여기는 인서트 게터 : " + readDTO);

		model.addAttribute("readDTO", readDTO);
		model.addAttribute("HOME", "READ_INSERT");

		return "template";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@ModelAttribute ReadDTO readDTO, Model model, SessionStatus sStatus) {

		log.debug("여기는 인서트 포스트 : " + readDTO);

		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

		SimpleDateFormat st = new SimpleDateFormat("HH:mm:ss");

		String dumy = sd.format(date);
		String dumys = st.format(date);

		if (readDTO.getRb_date().isEmpty()) {
			readDTO.setRb_date(dumy);
			readDTO.setRb_stime(dumys);
		}

		rService.insert(readDTO);

		sStatus.setComplete();

		return "redirect:/read/view-code?id=" + readDTO.getRb_bcode();
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@RequestParam("id") long rb_seq, Model model, HttpSession hSession) {

		MemberDTO memberDTO = (MemberDTO) hSession.getAttribute("memberDTO");

		if (memberDTO == null) {
			model.addAttribute("BODY", "LOGIN");
			model.addAttribute("LOGIN_MSG", "TRY");
			return "redirect:/member/login";
		}
		ReadDTO readDTO = rService.findByRbSep(rb_seq); // rDao.findByRBCode(rb_bcode);
		model.addAttribute("readDTO", readDTO);
		model.addAttribute("HOME", "READ_INSERT");

		return "template";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute("readDTO") ReadDTO readDTO, SessionStatus sStatus) {

		rService.update(readDTO);
		sStatus.setComplete();

		return "redirect:/";

	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("id") long rb_seq, HttpSession hSession, Model model) {

		MemberDTO memberDTO = (MemberDTO) hSession.getAttribute("memberDTO");

		if (memberDTO == null) {
			model.addAttribute("BODY", "LOGIN");
			model.addAttribute("LOGIN_MSG", "TRY");
			return "redirect:/member/login";
		}

		rService.delete(rb_seq);

		return "redirect:/";
	}

}
