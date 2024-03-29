package com.biz.memo.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.memo.domain.UserDTO;
import com.biz.memo.persistence.UserDao;

/*
 * interface를 implements하여 생성한 클래스에 @Service를 붙여주면
 * @Autowired를 수행할 때
 * Interface객체 형식으로 코드를 작성하면
 * 자동으로 해당 객체를 가져와서 객체를 초기화 하여준다
 */
@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	UserDao uDao;
	@Autowired
	public void newUserDao() {
		uDao = sqlSession.getMapper(UserDao.class);
	}
	
	
	@Override
	public int userJoin(UserDTO userDTO) {
		// TODO Auto-generated method stub
		
		// 이미 한명이라도 테이블에 정보가 저장된 상태라면
		// grade = "U"
		if(uDao.userCount() > 0) {
			userDTO.setU_grade("U");
			
		// 아니면 grade="A"
		}else {
			userDTO.setU_grade("A");
		}
		String cryptext = passwordEncoder.encode(userDTO.getU_password());
		userDTO.setU_password(cryptext);
		return uDao.userInsert(userDTO);
	}

	@Override
	public boolean userIdCheck(String u_id) {

		UserDTO userDTO = uDao.findById(u_id);
		/*
		 * else가 꼭잇어야 하는 코드
		if(userDTO == null) {
			return false;
		}else {
			return false;	
			
		}
		*/
		
		// else가 없어도 되는 코드
		// if 비교문에서 else가 없어도 되는 코드를 만들수 있으면
		// 가급적 else를 없이 사용하자
		  if(userDTO != null && userDTO.getU_id().equalsIgnoreCase(u_id)) {
		 
			
			return true;
		}
		return false;
		
		
	}

	@Override
	public boolean userLoginCheck(UserDTO userDTO) {
		// login에서 받아온 id = inU_id
		String inU_id = userDTO.getU_id();
		String inU_pass = userDTO.getU_password();
		
		// 암호화를 대비한 코드
		// id를 매개변수로 보내고
		// id로 조회를 하여 회원정보 받기
		UserDTO userRDTO = uDao.findById(inU_id);
		
		// 회원정보가 없을경우
		if(userRDTO == null) {
			return false;
		}
		
		// select로 조회한 id = sU_id
		String sU_id = userRDTO.getU_id();
		String sU_pass = userRDTO.getU_password();
		
		// DB table에 저장된 사용자 패스워드로
		// 암호화된 문자열이다.
		String cryptU_pass = userRDTO.getU_password();
		
		// 회원 id는 존재하지만 pw가 틀렸을 경우
		if(sU_id.equalsIgnoreCase(inU_id) && 
				// sU_pass.equals(inU_pass)) {
				
				// Bcrypt로 암호화된 문자열은 equal()등의 method로
				// matche 되는지 비교가 불가능하고
				// Bcrypt에서 지원하는 matches() method를 사용하여
				// matche 되는지를 비교
				passwordEncoder.matches(inU_pass, cryptU_pass)){
			
			
			/*
			 * java method에서 객체를 매개변수로 받은 후
			 *  각 필드변수를 개별적으로 변경을 하면
			 *  파라메터로 주입한 원본의 변수값들이 변경된다
			 *  
			 *  하지만
			 *  객체 = 다른객체
			 *  또는 객체 = new 클래스() 형식으로
			 *  자체를 변경하면 파라메터로 주입된 원본은 변경되지 않는다.
			 *  
			 *  그래서 이와같은 경우 각 필드요소들을 모두 주입해 주어야 한다.
			 */
			// userDTO = userRDTO;
			// userDTO.setU_grade(userRDTO.getU_grade());
			
			return true;
		}else {
			return false;
		}
		
	}


	@Override
	public UserDTO getUser(String u_id) {
		// TODO Auto-generated method stub
		return uDao.findById(u_id);
	}

}
