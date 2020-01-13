package com.biz.rbooks.repository;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.biz.rbooks.domain.ReadDTO;

public interface ReadDao {

	// @Select(ReadSQL.selectAll)
	@Select("SELECT * FROM tbl_read_book ORDER BY rb_seq")
	public List<ReadDTO> selectAll();

	@Select("SELECT * FROM tbl_read_book WHERE rb_seq = ${rb_seq}")
	public ReadDTO findByRBCode(long rb_seq);
	
	@Select("SELECT * FROM tbl_read_book WHERE rb_seq = ${rb_seq}")
	public List<ReadDTO> findByRbSeq(long rb_seq);
	
	@InsertProvider(type=ReadSQL.class, method="r_insert_sql")
	public int insert(ReadDTO readDTO);
	
	@UpdateProvider(type=ReadSQL.class, method="r_update_sql")
	public int update(ReadDTO readDTO);

	@Select("DELETE FROM tbl_read_book WHERE rb_seq=#{rb_seq}")
	public ReadDTO delete(long rb_seq);

	
}
