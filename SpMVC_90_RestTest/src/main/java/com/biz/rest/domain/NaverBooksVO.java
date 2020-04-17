package com.biz.rest.domain;

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
public class NaverBooksVO {

	private String total;
	private String title;
	private String link;
	private String image;
	private String author;
	private String price;
	private String publisher;
	private String isbn;
	private String pubdate;
}
