package com.biz.product.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biz.product.domain.ProductDTO;
import com.biz.product.service.ProductService;

/*
 * 지금부터 이 Controller는 RESTful 서비스를 response를 할 객체다라고 선언
 * 이 컨트롤러에서 모든 method는 절대 view를 리턴할 수 없다.
 * 실질적으로는 Model, ModelAndView 클래스를 사용하지 않아도 된다.
 */
@RequestMapping(value="/rest")
@RestController
public class ProductRestController {

	@Autowired
	ProductService pService;
	
	@RequestMapping(value="/getProduct", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ProductDTO getProduct(String p_code) {
		
		ProductDTO proDTO = pService.findByPCode(p_code);
		
		return proDTO;
		
	}
	
	
	// getString이라는 메소드로 호출
	@RequestMapping(value="/getString",method=RequestMethod.GET, 
	
	// @ResponseBody를 사용할 때는
	// 꼭 produces를 설정하는것이 좋다.
	// 특히 한글 데이터를 response할 때는 반드시
	// charset=UTF-8은 꼭 있어야 한다.
	produces = "text/json;charset=UTF-8")
	public String getString(
											// query로 보내는 변수명
											@RequestParam(value="str", 
											
											// require=false와 defaultValue가 없으면
											// server는 client 에게 400오류를 보내고 처리를거부한다.
											// 절대 사용자가 만든 vo, dto에는 적용하면 안된다.
											
											// 혹시 값을 보내지 않아도 오류를 내지마라
											required =false,  
											
													// 값을보내지않으면 없음이라는 문자열 세팅
											defaultValue="없음")String myStr) { 
		
		if(myStr.equals("없음")) {
			return "url?str=문자열 형식으로 보내라";
		}else {
			return "보낸 문자열은 ? : " + myStr;
		}
		
	}
	
	
	@RequestMapping(value = "/getUUID", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public String getUUID() {
		
		String strUUID = UUID.randomUUID().toString();
		return strUUID + ":" + strUUID.length();

	}
}
