package com.biz.rbooks.service;

import org.springframework.stereotype.Service;

import com.biz.rbooks.domain.PageDTO;

@Service
public class PageService {

	private int listPerPage = 9; // 한페이지에 보여질 데이터 개수
	private int pageCount = 3; // 화면하단에 페이지 버튼 개수
	
	// this로 재주입하는 이유는
	// 해당변수가 private로 걸려있기 때문에 다른 클래스에서 사용할수가 없다.
	// 그러므로 매개변수로 
	public void setListPerPage(int listPerPage) {
		this.listPerPage = listPerPage;
	}
	
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	// totalCount 데이터 총 개수 
	// currentPageNo 현재페이지번호
	public PageDTO getPagination(long totalCount, int currentPageNo) {

		if(totalCount < 1) {
			return null;
		}
		
		// 전체데이터의 마지막 페이지는 
		// 데이터 총 개수 + (한페이지에 보여질 개수 -1 ) / 한페이지에 보여질 개수
		// ex )  n = ( 100 + (10 - 1) ) / 10
		// n = 10.9가 되지만 int형이기때문에 소수점자리가 잘린다.
		// 즉 finalPageNo = 10이 된다.
		int finalPageNo = ( (int)totalCount + (listPerPage - 1) ) / listPerPage;
		
		// 현재 페이지 번호가 마지막페이지번호보다 클 때 
		// 현재 페이지번호가 마지막 페이지번호이다.
		if(currentPageNo > finalPageNo) {
			currentPageNo = finalPageNo;
		}
		
		// 1이하로 갈수가 없는데 왜 해당 if문이 필요한가.
		if(currentPageNo < 1) {
			currentPageNo = 1;
		}
		
		// startPageNo : 화면에 보여질 첫 페이지 번호 버튼
		// ex) ((7-1)/10) * 10 + 1
		int startPageNo = ((currentPageNo - 1) / pageCount ) * pageCount + 1;
		
		// endPageNo : 화면에 보여질 마지막 페이지 번호 버튼
		int endPageNo = startPageNo + pageCount -1 ;
		
		if(endPageNo > finalPageNo) {
			endPageNo = finalPageNo;
		}
		
		// prePageNo : 이전으로가기 페이지번호
		int prePageNo = 1;
		// 현재페이지번호가 1보다 크다면
		// 이전으로가기버튼의 번호는 현재페이지 번호 - 1이다.
		if((currentPageNo) > 1) {
			prePageNo = currentPageNo -1;
		}
		
		int nextPageNo = finalPageNo;
		if(currentPageNo < finalPageNo) {
			nextPageNo = currentPageNo + 1;
		}
		
		// offset : 1, 2, 3 페이지를 선택했을 때
		// 시작번호를 1, 11, 21로 설정 	
		int offset = (currentPageNo -1) * listPerPage + 1;
		
		// 화면에 보여지는 끝번호를 10, 20, 30으로 설정하기
		int limit = offset + listPerPage - 1;
		
		PageDTO pageDTO = PageDTO.builder()
				.totalCount(totalCount)
				.pageCount(pageCount)
				
				.listPerPage(listPerPage)
				.offset(offset)
				.limit(limit)
				
				.firstPageNo(1)
				.finalPageNo(finalPageNo)
				
				.startPageNo(startPageNo)
				.endPageNo(endPageNo)
				
				.prePageNo(prePageNo)
				.nextPageNo(nextPageNo)
				
				.currentPageNo(currentPageNo)
				.build();
		
		return pageDTO;
				
	}
	
	
	
	
}
