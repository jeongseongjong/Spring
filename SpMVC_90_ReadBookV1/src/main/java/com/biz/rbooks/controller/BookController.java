package com.biz.rbooks.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.rbooks.domain.BooksDTO;
import com.biz.rbooks.domain.MemberDTO;
import com.biz.rbooks.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BookController {

	@Autowired
	BookService bService;
	
	@RequestMapping(value="blist",method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getBlist(Model model) {
		
		List<BooksDTO> bookList = bService.selectAll();
		
		model.addAttribute("BLIST", bookList);
		
		log.debug("북리스트" + bookList.toString());
		
		return "home";
	}
	
	@ResponseBody
	@RequestMapping(value="getBooks",method=RequestMethod.GET)
	public BooksDTO getBooks(@RequestParam("id") String b_code,Model model) {
		
		BooksDTO booksDTO = bService.findByBCode(b_code);
		
		model.addAttribute("booksDTO", booksDTO);
		return booksDTO;
	}
	
	@RequestMapping(value="bookList", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getBookNames(@RequestParam("search")String b_name, Model model){
		
		List<BooksDTO> bookList = bService.findByBNames(b_name);
		log.debug("search 리스트" + bookList.toString());
		model.addAttribute("BLIST", bookList);
		
		return "home";
	}
	
	@ResponseBody
	@RequestMapping(value="bname",method=RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String getBName(String b_code) {
		
		BooksDTO booksDTO = bService.findByBCode(b_code);
		
		return "<h1>" + booksDTO.getB_name() + "</h1>";
	}
	
	@RequestMapping(value="iprice", method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String getIPrice(String b_code) {
		
		BooksDTO booksDTO = bService.findByBCode(b_code);
		
		return booksDTO.getB_iprice();
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(@ModelAttribute("booksDTO")BooksDTO booksDTO, Model model, HttpSession hSession) {
		
		MemberDTO memberDTO = (MemberDTO) hSession.getAttribute("memberDTO");
		
		if(memberDTO == null) {
			model.addAttribute("BODY", "LOGIN");
			model.addAttribute("LOGIN_MSG", "TRY");
			return "redirect:/member/login";
		}else {
			model.addAttribute("BODY", "READ_WR");
			model.addAttribute("booksDTO", booksDTO);
		}
			
		
		return "insert";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	// insert.jsp에 있는 form:modelAttribute 값인 booksDTO를 이 메소드로 가져왔다.
	// @ModelAttribute는 일반적으로 해당 모델타입의 앞글자를 소문자로 바꾼 이름이 key값이 된다.
	public String insert(@ModelAttribute BooksDTO booksDTO, Model model, SessionStatus sStatus) {
		
		log.debug("저는 인서트 포스트에서 날라온 친구에요 " + booksDTO.toString());
		
		bService.insert(booksDTO);
		
		sStatus.setComplete();
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	// update로가는 jsp문에서 경로값을 @requestParam("??")과 같은 변수로 지정해준다. 
	// ex) <a href= "${rootPath}/update?b_code=${vo.b_code}">
	public String update(@RequestParam("id")String b_code, Model model, HttpSession hSession) {
		
		MemberDTO memberDTO = (MemberDTO) hSession.getAttribute("memberDTO");
		
		if(memberDTO == null) {
			model.addAttribute("BODY", "LOGIN");
			model.addAttribute("LOGIN_MSG", "TRY");
			return "redirect:/member/login";
		}
		
		log.debug("bcode" + b_code);
		
		BooksDTO booksDTO = bService.findByBCode(b_code);
		log.debug("업데이트" + booksDTO.toString());
		model.addAttribute("booksDTO", booksDTO);
		return "insert";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(@ModelAttribute("booksDTO") BooksDTO booksDTO,Model model, SessionStatus status) {
		
		bService.update(booksDTO);
		
		status.setComplete();
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(@RequestParam("id") String b_code, Model model, HttpSession hSession) {
		
		MemberDTO memberDTO = (MemberDTO) hSession.getAttribute("memberDTO");
		
		if(memberDTO == null) {
			model.addAttribute("BODY", "LOGIN");
			model.addAttribute("LOGIN_MSG", "TRY");
			return "redirect:/member/login";
		}
		bService.delete(b_code);
		
		return "redirect:/";
	}
	
}
