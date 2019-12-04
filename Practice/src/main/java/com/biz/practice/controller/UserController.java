package com.biz.practice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.practice.domain.UserDTO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {
	Scanner scan = new Scanner(System.in);
	
	@RequestMapping(value="userInput", method=RequestMethod.GET)
	public String userInput() {
		return "user/userInput";
	}
	@RequestMapping(value="userInput", method=RequestMethod.POST)
	public String userInput(UserDTO userDTO, Model model) {
		System.out.println(userDTO.toString());
		model.addAttribute("userDTO",userDTO);
		return "user/userList";
		
	}
	
	@RequestMapping(value="userList", method=RequestMethod.GET)
	public String userList(Model model, UserDTO userDTO) {
		List<UserDTO> userList = new ArrayList<UserDTO>();
		String strId = scan.nextLine();
		userDTO.setU_id(strId);
		String strPw = scan.nextLine();
		userDTO.setU_pw(strPw);
		String strName = scan.nextLine();
		userDTO.setU_name(strName);
		int intNuM = scan.nextInt();
		userDTO.setU_num(intNuM);
		String strTel = scan.nextLine();
		userDTO.setU_tel(strTel);
		String strAddr = scan.nextLine();
		userDTO.setU_add(strAddr);
		userList.add(userDTO);
		System.out.println(userList);
		
		return "user/userList";
	}
	
}
