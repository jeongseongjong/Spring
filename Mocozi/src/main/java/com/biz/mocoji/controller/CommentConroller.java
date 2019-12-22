package com.biz.mocoji.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.mocoji.service.CommentService;
import com.biz.mocoji.service.TourAppService;

@RequestMapping(value = "/comment")
@Controller
public class CommentConroller {

	@Autowired
	TourAppService tService;
	
	@Autowired
	CommentService cService;
	
	@RequestMapping(value="/write", method = RequestMethod.POST, produces="text/json;charset=UTF-8")
	public String writeComment(@RequestParam(value = "contentid", required = false, defaultValue = "1")String contentId, 
								@RequestParam(value = "areacode", required = false, defaultValue = "1")String areacode,
								@RequestParam(value = "sigungucode", required = false, defaultValue = "1")String sigungucode,
								@RequestParam(value = "writer", required = false, defaultValue = "1")String c_writer,
								@RequestParam(value = "text", required = false, defaultValue = "1")String c_text,
								Model model) {
		
		model.addAttribute("contentid", contentId);
		model.addAttribute("areacode", areacode);
		model.addAttribute("sigungucode", sigungucode);
		
		int ret = cService.insertComment(contentId, c_writer, c_text, areacode, sigungucode);
		
		return "redirect:/tour/detail";
		
	}
	
}
