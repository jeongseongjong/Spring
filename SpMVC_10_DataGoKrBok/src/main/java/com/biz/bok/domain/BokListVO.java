package com.biz.bok.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BokListVO {

	// xml에서 봤던 WantedList안에 servList에 들어있는 항목을 멤버변수로 만든 것
	private String servId;			//	서비스ID
	private String servNm; 			//	서비스명
	private String jurMnofNm; 		//	소관부처명
	private String jurOrgNm; 		//	소관조직명
	private String inqNum; 			//	조회수
	private String servDgst;		//	서비스 요약
	private String servDtlLink; 	//	서비스상세링크 
	private String svcfrstRegTs; 	//	서비스등록일

	
}
