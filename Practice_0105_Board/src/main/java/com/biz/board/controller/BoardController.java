package com.biz.board.controller;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.board.domain.BoardDTO;
import com.biz.board.domain.UserDTO;
import com.biz.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value="/board")
@SessionAttributes("boardDTO")
@Controller
public class BoardController {

	@Autowired
	BoardService bService;
	
	@ModelAttribute("boardDTO")
	public BoardDTO makeBoardDTO() {
		BoardDTO boardDTO = new BoardDTO();
		
		return boardDTO;
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(String search, Model model) {
		
		List<BoardDTO> boardList;
		
		if(search == null || search.isEmpty()) {
			boardList = bService.getAllList();
		}else {
			boardList = bService.getSearchList(search);
		}
		
		model.addAttribute("BODY", "BOARD_LIST");
		model.addAttribute("BOARD_LIST", boardList);
		return "home";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(@ModelAttribute("boardDTO")BoardDTO boardDTO, Model model, HttpSession httpSession) {
		
		UserDTO userDTO = (UserDTO) httpSession.getAttribute("userDTO");
		
		if(userDTO == null) {
			
			// "BODY"라는 이름으로 "LOGIN"이라는 문자열을 담겠다.
			model.addAttribute("BODY", "LOGIN");
			model.addAttribute("LOGIN", "TRY");
			
			return "redirect:/board/login";
			
		}
		
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		
		boardDTO.setB_date(sd.format(date));
		boardDTO.setB_auth(userDTO.getU_id());
		
		model.addAttribute("BODY", "BOARD_WR");
		model.addAttribute("boardDTO", boardDTO);
		
		return "board/insert";
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(@ModelAttribute BoardDTO boardDTO, String search, Model model, SessionStatus sStatus) {
		
		int ret = bService.insert(boardDTO);
		
		sStatus.setComplete();
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String view(@RequestParam("id") String str_seq, @ModelAttribute BoardDTO boardDTO, Model model, HttpSession hSession) {
		
		long b_seq = 0;
		
		try {
			b_seq = Long.valueOf(str_seq);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		boardDTO = bService.getBoard(b_seq);
		UserDTO userDTO = (UserDTO) hSession.getAttribute("userDTO");
		
		if(userDTO != null && userDTO.getU_id().equals(boardDTO.getB_auth())){
			
			model.addAttribute("boardDTO", boardDTO);
			model.addAttribute("BODY", "BOARD_VI");
			
			return "board/view";
			
		}else {
			model.addAttribute("LOGIN_MSG", "NO_AUTH");
			model.addAttribute("BODY", "LOGIN");
			
			return "redirect:/user/login";
		}
		
	}
			
}






















