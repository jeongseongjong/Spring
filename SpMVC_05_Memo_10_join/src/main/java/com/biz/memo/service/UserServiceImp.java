package com.biz.memo.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.memo.domain.HobbyDTO;
import com.biz.memo.domain.UserDTO;
import com.biz.memo.domain.UserHobbyDTO;
import com.biz.memo.persistence.UserDao;

@Service
public class UserServiceImp  implements UserService {

	@Autowired
	SqlSession sqlSession;
	UserDao userDao ;
	
	@Autowired
	public void newUserDao() {
		userDao = sqlSession.getMapper(UserDao.class);
		
	}
	
	@Override
	public int userJoin(UserDTO userDTO) {

		/*
		 * 최초로 등록되는 사용자는 관리자이다
		 * u_grade 값을 'A'로 세팅한다.
		 */
		if(userDao.userCount() > 0){
			userDTO.setU_grade("U");
		}else {
			userDTO.setU_grade("A");
		}
		int ret = userDao.insert(userDTO);
		
		
		String[] strHobby = userDTO.getU_hobby();
		for(String s : strHobby) {
			UserHobbyDTO uh = UserHobbyDTO.builder()
					.uh_id(userDTO.getU_id())
					.uh_code(s).build();
			
			userDao.uHInsert(uh);
		}
		
		
		return ret;
	}

	@Override
	public boolean userIdCheck(String u_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void userLoginCheck(UserDTO userDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int userUpdate(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void userOut(UserDTO userDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<HobbyDTO> getHobbyList() {
		
		return userDao.selectAllHobby();
	}

	
}
