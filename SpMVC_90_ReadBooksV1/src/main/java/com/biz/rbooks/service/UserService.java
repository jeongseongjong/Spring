package com.biz.rbooks.service;

import javax.validation.Valid;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.rbooks.domain.MemberDTO;
import com.biz.rbooks.repository.UserDao;

@Service
public class UserService {

	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	BCryptPasswordEncoder passwordENcoder;
	
	UserDao uDao;
	
	
	@Autowired
	public UserService(UserDao uDao) {
		super();
		this.uDao = uDao;
	}

	@Autowired
	public void newUserDao() {
		uDao = sqlSession.getMapper(UserDao.class);
	}
	
	public int userJoin(@Valid MemberDTO memberDTO) {
		String cryptext = passwordENcoder.encode(memberDTO.getM_password());
		memberDTO.setM_password(cryptext);
		
		return uDao.userInsert(memberDTO);
	}
	
	public boolean userIdCheck(String m_id) {
		
		MemberDTO memberDTO = uDao.findById(m_id);
		
		if(memberDTO != null && memberDTO.getM_id().equalsIgnoreCase(m_id)) {
			return true ;
		}
		return false;
	}
	
	public boolean userLoginCheck(MemberDTO memberDTO) {
		
		String inM_id = memberDTO.getM_id();
		String inM_pass = memberDTO.getM_password();
		
		MemberDTO memberRDTO = uDao.findById(inM_id);
		
		if(memberRDTO == null) {
			return false;
		}
		
		String sM_id = memberRDTO.getM_id();
		
		String cryptM_pass = memberRDTO.getM_password();
		
		if(sM_id.equalsIgnoreCase(inM_id) && passwordENcoder.matches(inM_pass, cryptM_pass)) {
			
			return true;
		}else {
			return false;
		}
	}
	
	public MemberDTO getUser(String m_id) {
		
		return uDao.findById(m_id);
	}
}















