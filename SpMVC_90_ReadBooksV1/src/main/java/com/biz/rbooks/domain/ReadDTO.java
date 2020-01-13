package com.biz.rbooks.domain;

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
public class ReadDTO {

	private long rb_seq;		//	number
	private String rb_bcode; 	//	varchar2(20)
	private String rb_date;	//	varchar2(10)
	private String rb_stime;	//	varchar2(10)
	private long rb_rtime;	//	number(10,3)
	private String rb_subject;	//	nvarchar2(20)
	private String rb_text;	//	nvarchar2(400)
	private int rb_star;	//	number

}
