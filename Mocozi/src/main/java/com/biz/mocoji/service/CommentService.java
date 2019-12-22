package com.biz.mocoji.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.mocoji.domain.CommentDTO;
import com.biz.mocoji.persistence.CommentDao;

@Service
public class CommentService {

	@Autowired
	SqlSession sqlSession;
	CommentDao cDao;
	
	@Autowired
	public void getDao() {
		cDao = sqlSession.getMapper(CommentDao.class);
	}
	
	// 테스트용 method
	public List<CommentDTO> selectAll(){
		
		List<CommentDTO> list = cDao.selectAll(); 
		
		return list;
	}
	
	public int insertComment(String c_contentid, String c_writer, String c_text, String c_areacode, String c_sigungucode) {
		
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		String curDate = sd.format(date);
		
		CommentDTO commentDTO = CommentDTO.builder().c_writer(c_writer).c_contentid(c_contentid).c_date(curDate).c_text(c_text).c_areacode(c_areacode).c_siguncode(c_sigungucode).build();
		
		int ret = cDao.insert(commentDTO);
		
		return ret;
	}
	
	public List<CommentDTO> viewCommentList(String contentId){
		
		List<CommentDTO> commentList = cDao.findByCommentId(contentId);
		
		for (CommentDTO commentDTO : commentList) {
			
			commentDTO.setC_text(commentDTO.getC_text().replace("\n", "<br>"));
			
		}
		
		return commentList;
		
	}
	
}
