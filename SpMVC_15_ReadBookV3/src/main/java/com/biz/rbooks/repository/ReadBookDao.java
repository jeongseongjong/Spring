package com.biz.rbooks.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.biz.rbooks.domain.ReadBookVO;
// @Mapper : 해당 클래스의 Dao를 맵퍼로 각인시켜주는 어노테이션 (해주나 안해주나 관계x)
@Mapper
public interface ReadBookDao {

	@Select("SELECT * FROM tbl_read_book")
	List<ReadBookVO> selectAll();

}
