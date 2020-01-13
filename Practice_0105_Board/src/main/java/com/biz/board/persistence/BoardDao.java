package com.biz.board.persistence;

import java.util.List;

import com.biz.board.domain.BoardDTO;

public interface BoardDao {

	public List<BoardDTO> selectAll();
	
	public BoardDTO findById(long b_seq);
	
	public int insert(BoardDTO boardDTO);
	public int update(BoardDTO boardDTO);
	public int delete(long b_seq);
}
