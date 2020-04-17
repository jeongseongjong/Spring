package com.biz.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.rest.domain.NaverBooksVO;
import com.biz.rest.domain.RestDTO;
import com.biz.rest.service.NaverSearchService;
import com.biz.rest.service.RestService;

@Controller
public class RestController {

	@Autowired
	RestService rService;

	@Autowired
	NaverSearchService naverBook;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(Model model) {

		List<RestDTO> restList = rService.selectAll();

		model.addAttribute("restList", restList);

		return "home";
	}

	@RequestMapping(value = "/searchNaver", method = RequestMethod.POST)
	public String searchNaver(@RequestParam(value = "searchNaver", required = false, defaultValue = "") String search,
			Model model) {

		try {
			List<NaverBooksVO> list = naverBook.getBooksList(search);
			model.addAttribute("naverSearch", list);
		} catch (Exception e) {

			model.addAttribute("naverSearch", null);
			e.printStackTrace();
		}

		return "naverSearch";
	}
}
