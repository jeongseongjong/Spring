package com.biz.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.biz.product.domain.ProductDTO;
import com.biz.product.service.FileService;
import com.biz.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProductController {

	@Autowired
	ProductService pService;
	
	@Autowired
	FileService fService;

	// @ResponseBody
	@RequestMapping(value = "plist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getPlist(Model model) {

		List<ProductDTO> proList= pService.selectAll();
		
		model.addAttribute("PLIST", proList);

		return "home";
	}
	
	@RequestMapping(value="input", method=RequestMethod.POST)
	public String input(
			@ModelAttribute
			ProductDTO proDTO,
			
			@RequestParam("u_file") MultipartFile u_file) {
		
		log.debug("상품정보입력 : " + proDTO.toString());
		log.debug("업로드한 파일 : " + u_file.getOriginalFilename());
		
		// 파일을 선택하지 않았으면
		// 파일에 관한 처리를 수행하지 말라
		// 업로드할 파일을 선택했을 때만 파일에 관한 처리를 하라
		if(!u_file.isEmpty()) {
			
			/*
			 * update를 수행할 때
			 * 이미 업로드된 파일이 있으면 기존의 파일을 삭제하고
			 * 새로운 파일을 업로드 해야하므로
			 * p_file 변수를 검사하여 값이 있으면
			 * 파일을 삭제하자
			 */
			if(proDTO.getP_file() != null) {
				fService.fileDelete(proDTO.getP_file());
			}
			
			
			/*
			 * web에서 파일이 전송되어 오면
			 * fService.fileUp() method에게 파일을 어딘가에 저장해 달라고 요청
			 */
			String upFileName = fService.fileUp(u_file);
			if(upFileName != null) {
				
				/*
				 * 정상적으로 저장이 완료되면
				 * 파일이름을 DTO의 p_file변수에 저장을 한다.
				 */
				proDTO.setP_file(upFileName);
			}
			
		}
		
		// insert 부분
		int ret = 0;
		if(proDTO.getP_code().isEmpty()) {
			log.debug("새로운 상품등록");
			
			ret = pService.insert(proDTO);
		}else {
			log.debug("기존 상품변경");
			
			// update 부분
			ret = pService.update(proDTO);
		}
		// redirect: (insert)본인의 일이 끝났으니 (plist)너가 할 일을 가져가라
		// redirect사용을 안하려면 model에 담아 home에 보내주는 return을 사용해야한다.
		return "redirect:/plist";
	}
	
	@RequestMapping(value="imgDelete",method=RequestMethod.GET)
	public String imgDelete(String p_code) {
		
		ProductDTO proDTO = pService.findByPCode(p_code);
		
		log.debug("PRODTO " + proDTO.toString());
		log.debug("PRO file " + proDTO.getP_file());
		
		if(proDTO.getP_file() != null && !proDTO.getP_file().isEmpty()) {
			
			fService.fileDelete(proDTO.getP_file());
			proDTO.setP_file(null);
			
			pService.update(proDTO);
		
		}
		return "redirect:/plist";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * @ResponseBody
	 * 결과물을 views(*.jsp)로 전환하지 않고
	 * 문자열 그대로 또는
	 * 객체(vo, dto)를 json 형태로 변경하여
	 * 클라이언트에게 response를 수행하는 기능 
	 */
	/*
	@ResponseBody
	@RequestMapping(value="getProduct", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public ProductDTO getProduct(String p_code) {
		
		ProductDTO proDTO = pService.findByPCode(p_code);
		
		return proDTO;
		
	}
	*/
	
	/*
	 * 문자열이나 객체에 담긴 데이터를 그대로 client에게 전송
	 */
	@ResponseBody
	
	// getString이라는 메소드로 호출
	@RequestMapping(value="getString",method=RequestMethod.GET, 
	
	// @ResponseBody를 사용할 때는
	// 꼭 produces를 설정하는것이 좋다.
	// 특히 한글 데이터를 response할 때는 반드시
	// charset=UTF-8은 꼭 있어야 한다.
	produces = "application/json;charset=UTF-8")
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
	
	
	@ResponseBody
	@RequestMapping(value = "plist/name", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public List<ProductDTO> getNames(String p_name) {

		List<ProductDTO> proList= pService.findByPNames(p_name);

		return proList;
	}
	
	
	/*
	 * produces의 content-Type
	 * 서버에서 문자열, 객체 등의 실제 데이터를 response할 때
	 * 어떤 type으로 보낼것인지 나타내는 문자열 
	 */
	@ResponseBody
	@RequestMapping(value = "pname", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String getPName(String p_code) {

		ProductDTO proDTO = pService.findByPCode(p_code);

		// return proDTO.getP_name();
		return "<h1>" + proDTO.getP_name() + "</h1>"; 
	}

	@ResponseBody
	@RequestMapping(value = "oprice", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String getOPrice(String p_code) {

		ProductDTO proDTO = pService.findByPCode(p_code);

		return proDTO.getP_oprice() + "";
	}

	@ResponseBody
	@RequestMapping(value = "iprice", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String getIPrice(String p_code) {

		ProductDTO proDTO = pService.findByPCode(p_code);

		return proDTO.getP_iprice() + "";
	}
}
