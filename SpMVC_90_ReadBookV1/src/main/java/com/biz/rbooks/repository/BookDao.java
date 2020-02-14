package com.biz.rbooks.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import com.biz.rbooks.domain.BooksDTO;
import com.biz.rbooks.domain.PageDTO;

@Repository
public interface BookDao {

	 @Select("SELECT * FROM tbl_books")
	 public List<BooksDTO> selectAll();
	
	@InsertProvider(type=BookSQL.class,method="insert_sql")
	public int insert(BooksDTO booksDTO);
	
	@UpdateProvider(type=BookSQL.class,method="update_sql")
	public int update(BooksDTO booksDTO);
	
	@Delete("DELETE FROM tbl_books WHERE b_code = #{b_code}")
	public int delete(String b_code);
	
	@Select("SELECT * FROM tbl_books WHERE b_name LIKE '%' || #{b_name,jdbcType=VARCHAR} || '%' ")
	public List<BooksDTO> findByBNames(String b_name);
	
	@Select("SELECT * FROM tbl_books WHERE b_code = #{b_code}")
	public BooksDTO findByBCode(String b_code);

	@Select("SELECT COUNT(*) FROM tbl_books")
	public long proTotalCount();
	
	@Select("SELECT COUNT(*) FROM tbl_books")
	public long searchCount();
	
	@Select(BookSQL.selectPage)
	public List<BooksDTO> selectPagination(PageDTO pageDTO);
	
	@Select(BookSQL.search)
	public List<BooksDTO> searchPagination(@Param("pageDTO")PageDTO pageDTO, @Param("search")String search);
	
	
//	@Select("SELECT * FROM tbl_books WHERE B_NAME LIKE '%' || #{b_name,jdbcType=VARCHAR} || '%' ")
//	@Results(value = { @Result(property = "b_code", column = "b_code"),
//					@Result(property = "rbNameList", 
//					column = "rb_bname", 
//					javaType = List.class, 
//					many = @Many(select = "getBookNames")) })
//	public List<BooksDTO> findByName(String b_name);

}


















