package com.biz.rbooks.repository;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.biz.rbooks.domain.BooksDTO;
import com.biz.rbooks.domain.ReadDTO;

public interface ReadDao {

	// @Select(ReadSQL.selectAll)
	@Select("SELECT * FROM tbl_read_book ORDER BY rb_seq")
	@Results(value= {
			@Result(property = "rb_bcode", column = "rb_bcode"),
			@Result(property = "b_name_list", column = "rb_bcode", 
					javaType = List.class, many = @Many(select = "getBooks"))
	})
	public List<ReadDTO> selectAll();
	
	// 메소드가 위의 select = " "로 들어간다.
	@Select("SELECT * FROM tbl_books WHERE b_code = #{b_code}")
	public BooksDTO getBooks(String b_code);


	@Select("SELECT * FROM tbl_read_book WHERE rb_bcode = #{rb_bcode} ORDER BY rb_seq")
	public List<ReadDTO> findByRBCode(String rb_bcode);
	
	@Select("SELECT * FROM tbl_read_book WHERE rb_seq = #{rb_seq}")
	public ReadDTO findByRbSeq(long rb_seq);
	
	@InsertProvider(type=ReadSQL.class, method="r_insert_sql")
	public int insert(ReadDTO readDTO);
	
	@UpdateProvider(type=ReadSQL.class, method="r_update_sql")
	public int update(ReadDTO readDTO);

	@Select("DELETE FROM tbl_read_book WHERE rb_seq=#{rb_seq}")
	public ReadDTO delete(long rb_seq);

	
}
