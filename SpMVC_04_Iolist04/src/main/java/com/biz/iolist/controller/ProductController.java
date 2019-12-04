package com.biz.iolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.biz.iolist.domain.ProductDTO;
import com.biz.iolist.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value="/product")
@Controller
public class ProductController {
	
	@Autowired
	ProductService pService;
	
	/*
	 * 상품이름을 전달받아서 해당상품을 검색하여 보여주기 
	 */
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String search(String strText, Model model) {

		List<ProductDTO> proList = pService.selectNameSearch(strText);
		
		model.addAttribute("PRODUCTLIST", proList);
		return "product/list-body";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
		public ModelAndView list() {
		
		ModelAndView mView = new ModelAndView();
		List<ProductDTO> proList = pService.getAllList();
		mView.setViewName("/product/list");
		mView.addObject("PRODUCTLIST", proList);
		return mView;
	}
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String input(Model model) {
		
		return "product/input";
		
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String input(ProductDTO proDTO, Model model) {
		
		log.debug("상품정보 " + proDTO.toString());
		int ret = pService.insert(proDTO);
		
		return "redirect:/product/list";
		
	}
	
	@RequestMapping(value="/view",method=RequestMethod.GET)
	public String view(String p_code, Model model) {
		log.debug("상품코드 : " + p_code);
		ProductDTO proDTO = pService.findByPCode(p_code);
		model.addAttribute("PRODUCT_DTO", proDTO);
		return null;
	}
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(String id, Model model) {
		
		int let = pService.delete(id);
		
		return "redirect:/product/list";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String update(String id, Model model) {
		
		ProductDTO proDTO = pService.findByPCode(id);
		model.addAttribute("PI", proDTO );
		return "product/input";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(ProductDTO proDTO, Model model) {
		
		log.debug(proDTO.toString());
		
		int ret = pService.update(proDTO);
		return "redirect:/product/view?p_code=" + proDTO.getP_code();
	}
}
