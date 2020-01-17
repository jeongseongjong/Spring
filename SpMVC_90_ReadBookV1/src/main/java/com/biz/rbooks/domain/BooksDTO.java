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
public class BooksDTO {
	private String b_code;
	private String b_name;
	private String b_auther;
	private String b_comp;
	private String b_year;
	private String b_iprice;
}
