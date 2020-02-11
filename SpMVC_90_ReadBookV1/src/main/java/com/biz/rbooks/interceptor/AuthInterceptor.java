package com.biz.rbooks.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter{

	/*
	 * Dispatcher에서 Controller로 가는 도중 가로채기를 수행할 method
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		
		String urlPath = request.getRequestURL().toString();
		String uriPath = request.getRequestURI().toString();
		
		String msg = String.format("URL : %S, \n URI : %S", urlPath,uriPath);
		
		// login정보 확인
		// 1. rest로부터 session iD를 추출
		
		HttpSession hSession = request.getSession();
		
		// member Session을 확인하기 위해서 Attribute를 추출해서
		// Object 객체(sessionObj)에 담기
		Object sessionObj = hSession.getAttribute("memberDTO");
		
		// Object 객체가 null 확인
		// null이면 MEMBER Session 객체가 없다.
		// 없으면 Login이 되어있지 않다.
		if(sessionObj == null) {
			
			// context/image/upload에서 로그인 path로 redirect를 수행하는데
			// 경로지정이 애매하게 작동한다.
			// 현재 경로가 /image/update이기 때문에
			// ../ -> context로 부터 경로를 시작하라
			// gallery/image/../member/login
			
			// 로그인 (form)으로 redirect를 수행하여
			// login을 하도록 유도
			response.sendRedirect(request.getContextPath() + "/member/login");
			
			// 현재 로그인이 안되어 있으므로 dispatcher에게 
			// 더이상 다른일을 수행하지 말라
			return false;
		}
		
		log.debug(msg);
		log.debug("나는 interceptor입니다");
		
		// return true;
		// controller에게 전달하라
		return super.preHandle(request, response, handler);
		
		
	}

	
}
