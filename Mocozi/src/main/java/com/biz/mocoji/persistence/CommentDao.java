package com.biz.mocoji.persistence;

import java.util.List;

import com.biz.mocoji.domain.CommentDTO;

public interface CommentDao {

	public List<CommentDTO> selectAll();
	public List<CommentDTO> findByCommentId(String contentId);
	
	public int insert(CommentDTO commentDTO);
	
}
