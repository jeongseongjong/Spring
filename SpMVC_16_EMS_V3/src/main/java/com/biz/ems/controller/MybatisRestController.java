package com.biz.ems.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biz.ems.domain.EmailVO;
import com.biz.ems.service.MybatisService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/mybatis_rest")
public class MybatisRestController {

	private final MybatisService myService;
	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public List<EmailVO> list(){
		
		return myService.selectAll();
		
	}
}
