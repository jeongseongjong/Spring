package com.biz.rest.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.biz.rest.domain.RestDTO;

@Repository
public interface RestDao {

	@Select("SELECT * FROM tbl_books")
	public List<RestDTO> selectAll();

}
