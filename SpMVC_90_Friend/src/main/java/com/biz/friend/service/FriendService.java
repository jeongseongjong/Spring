package com.biz.friend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.friend.domain.FriendVO;
import com.biz.friend.persistance.FriendDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FriendService {

	private final FriendDao fDao;
	
	public List<FriendVO> selectAll() {

		
		return fDao.selectAll();
	}

	public List<FriendVO> searchName(String f_name) {

		
		return fDao.searchName(f_name);
	}

	public int insert(FriendVO friendVO) {
		// TODO Auto-generated method stub
		return fDao.insert(friendVO);
	}
	
	public int update(FriendVO friendVO) {
		
		return fDao.update(friendVO);
	}
	
	public FriendVO findById(long f_id) {
		
		
		return fDao.findById(f_id);
	}

	public int delete(long f_id) {

		
		return fDao.delete(f_id);
	}

}
