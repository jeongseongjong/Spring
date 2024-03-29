package com.biz.product.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biz.product.domain.ProFileDTO;

public interface FileDao {

	public List<ProFileDTO> fileList(List<ProFileDTO> fileList);
	
	public void fileInsert(ProFileDTO file);
	public void filesInsert(@Param("files") List<ProFileDTO> files, @Param("p_code") String p_code);
	
	// 파일을 개별적으로 삭제
	public void filesDelete(List<ProFileDTO> files);
	
	// 다수의 파일을 삭제
	// 여러개의 파일을 선택한 후 삭제
	public void fileDelete(long file_seq);

	public ProFileDTO findByFileSeq(String file_seq);

	public int fileDelete(String file_seq);

	public List<ProFileDTO> findByPCode(String p_code);
	
	// 상품정보가 삭제되었을 때 삭제
	// 상품테이블과 파일테이블을 외래키 연관을 해두면
	// 상품테이블의 레코드가 삭제 될때 파일 테이블의 정보를 삭제할 수 있다.
	
	// 여러개의 파일인 경우는 특별한 상황이 아니면
	// 파일정보를 update하는 것은 거의 없다.
	}
