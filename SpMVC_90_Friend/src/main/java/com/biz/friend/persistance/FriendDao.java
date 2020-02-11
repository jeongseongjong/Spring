package com.biz.friend.persistance;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.biz.friend.domain.FriendVO;

public interface FriendDao {

	@Select("SELECT * FROM tbl_friend")
	public List<FriendVO> selectAll();

	public List<FriendVO> searchName(String f_name);

	public int insert(FriendVO friendVO);

	public int update(FriendVO friendVO);

	@Delete("DELETE FROM tbl_friend WHERE f_id = #{f_id}")
	public int delete(long f_id);
	
	public FriendVO findById(long f_id);


}
