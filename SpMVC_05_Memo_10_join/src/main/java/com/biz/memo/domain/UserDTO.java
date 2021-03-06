package com.biz.memo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDTO {

	private String u_id;
	private String u_password;
	private String u_name;
	private String u_nick;
	private String u_grade;
	private String u_tel;
	
	// 사용자의 취미를 여러가지 중복선택할 수 있도록 
	// 기능구현을 위한 배열로 선언
	// checkbox와 연결되는 변수, 대응하는 변수
	private String[] u_hobby;
    
}
