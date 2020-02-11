package com.biz.ems.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class NaverTokenVO {

	// 네이버로부터 발급받은 token
	private String access_token; 		
	
	// (선택사항) 네이버에 token을 재발급 요청했을 때 받는 token
	private String refresh_token;	
	
	// (선택사항) 오류가 발생했을 때
	private String error; 			
	private String error_description;
	
	private String token_type; 		
	
	// 유효기간
	private int expires_in;
	
			
}
