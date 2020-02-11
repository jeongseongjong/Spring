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
public class NaverReturnAuth {

	// 출력 결과 클래스 
	private String code;
	private String state;
	private String error;
	private String error_description;

}
