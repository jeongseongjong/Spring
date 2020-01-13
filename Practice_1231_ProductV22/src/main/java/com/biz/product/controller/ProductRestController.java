package com.biz.product.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biz.product.domain.ProductDTO;
import com.biz.product.service.ProductService;

@RequestMapping(value="/rest")
@RestController
public class ProductRestController {

	@Autowired
	ProductService pService;
	
	@RequestMapping(value="/getProduct",method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public ProductDTO getProduct(String p_code) {
		
		ProductDTO proDTO = pService.findByPCode(p_code);
		
		return proDTO;
	}
	
	@RequestMapping(value="/getString", method=RequestMethod.GET, produces = "text/json;charset=UTF-8")	
	public String getString(@RequestParam(value="str", required=false, defaultValue="없음") String myStr) {
		
		if(myStr.equals("없음")) {
			return "url?str=문자열 형식으로 보내라";
		}
		else {
			return "보낸문자열은 ? : " + myStr;
		}
	}
	
	@RequestMapping(value="/getUUID", method=RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public String getUUID() {
		String strUUID = UUID.randomUUID().toString();
		return strUUID + ":" + strUUID.length();
	}
}
