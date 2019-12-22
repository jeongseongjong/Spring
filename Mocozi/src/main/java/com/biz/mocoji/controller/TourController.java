package com.biz.mocoji.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.mocoji.domain.AreaBaseDTO;
import com.biz.mocoji.domain.AreaDTO;
import com.biz.mocoji.domain.CommentDTO;
import com.biz.mocoji.domain.DetailCommonDTO;
import com.biz.mocoji.domain.DetailIntroDTO;
import com.biz.mocoji.domain.StayDTO;
import com.biz.mocoji.service.CommentService;
import com.biz.mocoji.service.TourAppService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value = "/tour")
@Controller
public class TourController {
	
	@Autowired
	TourAppService tService;
	
	@Autowired
	CommentService cService;

	@RequestMapping(value = "/main", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public String viewAreaList(Model model) {
		
		List<AreaDTO> areaList = null;
		List<AreaBaseDTO> areaBaseList = null;
		try {
			areaList = tService.getArea();
			areaBaseList = tService.getAreaBase("", 5);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("BASELIST", areaBaseList);
		model.addAttribute("AREALIST", areaList);
		
		return "main";
		
	}
	
	@RequestMapping(value = "/curlist", method=RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public String viewAreaBaseCurList(@RequestParam(value = "code", required = false, defaultValue = "1")String areaCode, Model model) {
		
		List<AreaBaseDTO> areaBaseList = null;
		List<AreaDTO> areaList = null;
		
		try {
			areaBaseList = tService.getAreaBase(areaCode, 20);
			areaList = tService.getArea();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.debug("행사 리스트 :" + areaBaseList.toString());
		
		model.addAttribute("AREACODE",areaCode);
		model.addAttribute("BASELIST", areaBaseList);
		model.addAttribute("AREALIST", areaList);
		
		return "curlist";
	}
	
	@RequestMapping(value = "/pastlist", method=RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public String viewAreaBasePastList(@RequestParam(value = "code", required = false, defaultValue = "1")String areaCode, Model model) {
		
		List<AreaBaseDTO> areaBaseList = null;
		List<AreaDTO> areaList = null;
		
		try {
			areaBaseList = tService.getAreaBase(areaCode, 100);
			areaList = tService.getArea();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.debug("행사 리스트 :" + areaBaseList.toString());
		
		model.addAttribute("AREACODE",areaCode);
		model.addAttribute("BASELIST", areaBaseList);
		model.addAttribute("AREALIST", areaList);
		
		return "pastlist";
	}
	
	
	// 지역코드로 구분하지 않고 전체 리스트를 조회하는 method
	@RequestMapping(value = "/alllist", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public String allAreaBaseList(Model model) {
		
		List<AreaBaseDTO> areaBaseList = null;
		
		try {
			areaBaseList = tService.getAreaBase("", 200);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("BASELIST", areaBaseList);
		
		return "pastlist";
	}
	
	@RequestMapping(value="/detail", method = RequestMethod.GET, produces="text/json;charset=UTF-8")
	public String viewdetailIntro(@RequestParam(value = "contentid", required = false, defaultValue = "1")String contentId, 
									@RequestParam(value = "areacode", required = false, defaultValue = "1")String areacode, 
									@RequestParam(value = "sigungucode", required = false, defaultValue = "1")String sigungucode, 
			Model model) {
		
		DetailCommonDTO detailCommonDTO= null;
		DetailIntroDTO detailIntroDTO = null;
		List<AreaDTO> areaList = null;
		List<StayDTO> stayList = null;
		List<CommentDTO> commentList = null;
		
		try {
			areaList = tService.getArea();
			detailCommonDTO = tService.getDetailCommon(contentId);
			detailIntroDTO = tService.getDtailIntro(contentId);
			stayList = tService.getAccommodation(areacode, sigungucode);
			commentList = cService.viewCommentList(contentId);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		model.addAttribute("AREACODE", areacode);
		model.addAttribute("AREALIST", areaList);
		model.addAttribute("stayList", stayList);
		model.addAttribute("detailCommonDTO", detailCommonDTO);
		model.addAttribute("detailIntroDTO", detailIntroDTO);
		model.addAttribute("commentList", commentList);
		
		return "detail";
	}
	
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam(value="keyword", required = false , defaultValue = "")String keyword, Model model) {
		
		List<AreaBaseDTO> searchList = null;
		try {
			searchList = tService.getSearchList(keyword);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("BASELIST", searchList);
		
		return "pastlist";
	}
	
	@RequestMapping(value="/introduce", method = RequestMethod.GET, produces="text/json;charset=UTF-8")
	public String introduce(Model model) {
		
		List<AreaDTO> areaList = null;
		List<AreaBaseDTO> areaBaseList = null;
		try {
			areaList = tService.getArea();
			areaBaseList = tService.getAreaBase("", 5);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("BASELIST", areaBaseList);
		model.addAttribute("AREALIST", areaList);
		
		
		return "introduce";
	}
	
}
