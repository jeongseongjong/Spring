package com.biz.crawl.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.biz.crawl.domain.NaverMovieVO;

public interface NaverDao {

	@Select("SELECT * FROM tbl_movie")
	public List<NaverMovieVO> selectAll();
	
	public int insert(NaverMovieVO mVO);
	public int insertAll(List<NaverMovieVO> mList);
	
	@Delete("delete from tbl_movie")
	public void deleteAll();
}
