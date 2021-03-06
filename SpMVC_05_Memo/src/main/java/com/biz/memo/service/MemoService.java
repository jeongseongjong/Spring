package com.biz.memo.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.memo.domain.MemoDTO;
import com.biz.memo.persistence.MemoDao;

// @Repository
@Service
public class MemoService {

	@Autowired
	SqlSession sqlSession;
	
	MemoDao mDao;
	
	/*
	 * Service를 사용하려고 시도하면
	 * sqlSession으로부터 MemoDao mapper를 추출하여
	 * mDao 객체를 사용할 수 있도록 초기화 하라
	 */
	@Autowired
	public void getMapper() {
		mDao = sqlSession.getMapper(MemoDao.class);
	}

	public List<MemoDTO> getAllList() {
		// 전체 메모리스트를 DB로 부터 가져와서 controller로 리턴
		
		return mDao.selectAll();
	}

	public List<MemoDTO> getSearchList(String m_subject) {
		// TODO 제목으로 검색
		
		MemoDTO memoDTO = MemoDTO.builder()
				.m_subject(m_subject)
				.build();
		return mDao.findBySearch(memoDTO);
	}

	public int insert(MemoDTO memoDTO) {

		return mDao.insert(memoDTO);
		
	}
}
