package com.biz.shop.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.shop.domain.Authorities;
import com.biz.shop.domain.Users;
import com.biz.shop.repository.AuthDao;
import com.biz.shop.repository.UserDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

	private final BCryptPasswordEncoder passEncoder;
	private final UserDao userDao;
	private final AuthDao authDao;
	
	// 먼젓번의 로직이 실행되지 않을시 실패로 보고 정해둔 곳으로 넘어가서 재실행한다.
	@Transactional  
	public void userSave(Users userVO) {
		
		String userRole = "ROLE_ADMIN";
		
		// 처음등록 사용자에게는 admin을 부여하고
		// 이후 사용자에게는 user를 부여하는 코드를 부착
		
		String planPass = userVO.getPassword();
		String cryptPass = passEncoder.encode(planPass);
		
		userVO.setPassword(cryptPass);
		userVO.setEnabled(true); // ID의 활성화
		
		Authorities auth = Authorities.builder()
				.username(userVO.getUsername())
				.authority(userRole)
				.build();
		
		userDao.save(userVO);
		authDao.save(auth);
	}
}
