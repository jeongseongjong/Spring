package com.biz.bok.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.bok.domain.BokSearchDTO;
import com.biz.bok.service.CodeService;

import lombok.extern.slf4j.Slf4j;

/**
 * Handles requests for the application home page.
 */
@Slf4j
@Controller
public class HomeController {
	
//	@Autowired
//	CodeService cService ;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
//		model.addAttribute("bokSearchDTO", new BokSearchDTO());
//		
//		// cService의 selectMaps를 jsp의 SeMap객체에 전달
//		model.addAttribute("SeMap", cService.getSelectMaps());
		return "redirect:/search";
	}
	
}
