package com.biz.rbooks.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Mapper;
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
import com.biz.rbooks.domain.PageDTO;
import com.biz.rbooks.service.BookService;
import com.biz.rbooks.service.PageService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BookController {

	@Autowired
	BookService bService;
	@Autowired
	PageService pageService;
	
	@RequestMapping(value="blist",method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getBlist(Model model, BooksDTO booksDTO, @RequestParam(value="currentPageNo", required=false, defaultValue= "1")int currentPageNo) {
		
		long totalCount = bService.totalCount();
		PageDTO pageDTO = pageService.getPagination(totalCount, currentPageNo);
		List<BooksDTO> bList = bService.selectPagination(pageDTO);		
		
		log.debug("요것은 pageDto" + pageDTO.toString());
		model.addAttribute("HOME", "BLIST");
		model.addAttribute("PLIST", bList);
		log.debug("요것은 pageList" + bList);
		model.addAttribute("controller", "blist");
		model.addAttribute("pageDTO", pageDTO);

		return "template";
	}
	
	@ResponseBody
	@RequestMapping(value="getBooks",method=RequestMethod.GET)
	public BooksDTO getBooks(@RequestParam("id") String b_code,Model model) {
		
		BooksDTO booksDTO = bService.findByBCode(b_code);
		
		model.addAttribute("booksDTO", booksDTO);
		return booksDTO;
	}
	
	@Mapper
	@RequestMapping(value="bookList", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getBookNames(@RequestParam("search")String search,
								@RequestParam(value="currentPageNo", required=false, defaultValue="1")int currentPageNo, 
								@RequestParam(value="text", required=false, defaultValue = "") String text,
								Model model){
		List<BooksDTO> bookList = bService.findByBNames(search);
		
		long searchCount = bookList.size();
		PageDTO pageDTO = pageService.getPagination(searchCount, currentPageNo);
		List<BooksDTO> BLIST = bService.searchPagination(pageDTO,search);
		
		log.debug("search 리스트" + bookList.toString());
		log.debug("페이지네이션 " + BLIST.toString());
		model.addAttribute("HOME","BLIST");
		model.addAttribute("search",search);
		model.addAttribute("PLIST", BLIST);
		model.addAttribute("pageDTO", pageDTO);
		model.addAttribute("controller", "bookList");
		
		return "template";
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
		
		List<BooksDTO> bookList = bService.selectAll();
		model.addAttribute("HOME", "INSERT");
		model.addAttribute("BLIST", bookList);
		
		if(memberDTO == null) {	
			model.addAttribute("BODY", "LOGIN");
			model.addAttribute("LOGIN_MSG", "TRY");
			return "redirect:/member/login";
		}else {
			model.addAttribute("BODY", "READ_WR");
			model.addAttribute("booksDTO", booksDTO);
		}
		return "template";
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
		model.addAttribute("HOME", "INSERT");
		model.addAttribute("booksDTO", booksDTO);
		return "template";
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
		try {
			bService.delete(b_code);
		} catch (Exception e) {

			return "redirect:/";
		}
		
		return "redirect:/";
	}
	
}
