package com.biz.ems.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.ems.domain.NaverMember;
import com.biz.ems.domain.NaverReturnAuth;
import com.biz.ems.domain.NaverTokenVO;
import com.biz.ems.service.NaverLoginService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value="/member")
public class MemberController {

	private final NaverLoginService nLoginService;
	
	@RequestMapping(value="/naver",method=RequestMethod.GET)
	public String naver_login() {
		
	String apiURL = nLoginService.oAuthLoginGet();
	log.debug("apiURL이 오냐마냐 : " + apiURL);
		
		return "redirect: " + apiURL;
		
	}
	
	/*
	 * 네이버에 로그인 요청을 보낼때
	 * 네이버가 return할 url 부분
	 * 외부에서 접속할 수 있는 URL 이어야 한다.
	 * 
	 * 네이버에 로그인이 성공하면
	 * 실제로 네이버에서 로그인 인증정보 를 보내준다.
	 */
	 // @ResponseBody
	@RequestMapping(value="/naver/ok",method=RequestMethod.GET)
	public String naver_ok(@ModelAttribute NaverReturnAuth naverOk, HttpSession httpSession) {
		
		NaverTokenVO nToken = nLoginService.oAuthAccessGetToken(naverOk);
		
		// 사용자 정보가 memberVO에 담겨서 넘어온 상태
		NaverMember memberVO = nLoginService.getNaverMemberInfo(nToken);
		
		httpSession.setAttribute("MEMBER", memberVO);
		
		return "redirect:/ems/list";
		
		
	}
}
