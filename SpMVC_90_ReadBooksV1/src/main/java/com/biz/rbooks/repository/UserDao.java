package com.biz.rbooks.repository;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.biz.rbooks.domain.MemberDTO;

@Repository
public interface UserDao {

	@InsertProvider(type=UserSQL.class,method="user_insert_sql")
	public int userInsert(MemberDTO memberDTO);
	
	@Select("SELECT * FROM tbl_member WHERE m_id = #{m_id}")
	public MemberDTO findById(String m_id);

}
