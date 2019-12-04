package com.biz.student.domain;

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
public class StudentVO {

	private String st_num;
	private String st_name;
	private String st_tel;
	private String st_addr;
	private int st_grade;
	private String st_dept;
}
