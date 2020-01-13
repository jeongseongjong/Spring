package com.biz.memo.service;

import com.biz.memo.domain.UserDTO;

public interface UserService {

	/*
	 * 회원가입 처리 : insert
	 * 회원가입처리를 할 때
	 * 최초의 가입을 할 때 그 사용자는 관리자이다.
	 * 아직 테이블에 회원정보가 아무것도 없는 상태에서
	 * 회원가입을 하면 해당 사용자의 u_grade칼럼을 "A"로 설정
	 * 이후 가입자는 일반사용자로 "U"로 설정
	 */
	
	public int userJoin(UserDTO userDTO);
	
	/*
	 * id 중복검사
	 * 새로 회원가입을 실시할 때
	 * 입력한 id가 기존 table에 저장되어 있는지 검사
	 * 
	 * u_id를 매개변수로 받아서
	 * table에서 회원 id를 검사한 후
	 * 이미 해당 id가 
	 * 테이블에 존재하면 true return
	 * 테이블에 없으면 false를 return
	 */
	public boolean userIdCheck(String u_id);
	
	/*
	 * 로그인 처리
	 * 회원 id, pw를 전달받아 정상정보인지 검사 
	 * 회원 id와 pw를 매개변수로 받아
	 * 해당 정보와 일치하는(id, pw 동시에) 값이 
	 * 있으면 true를 return
	 * 없으면 false를 return(회원정보가 없거나 pw 오류시)
	 */
	public boolean userLoginCheck(UserDTO userDTO);

	public UserDTO getUser(String u_id);
}
