package com.biz.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.biz.ems.domain.EmailVO;
import com.biz.ems.repository.EmailDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaveMailService {

	private final EmailDao emsDao;

	public int delete(long ems_seq) {

		emsDao.deleteById(ems_seq);
		return 0;
	}

	public int insert(EmailVO emailVO) {

		// insert 실행시 원래 id가 있으면(primary key가 vo에 담겨있으면)
		// update가 되고 데이터가 없으면 insert가 된다.
		emsDao.save(emailVO);

		return 0;
	}

	public List<EmailVO> selectAll() {

		List<EmailVO> mailList = (List<EmailVO>) emsDao.findAll();

		return mailList;
	}

	public EmailVO findById(long ems_seq) {

		Optional<EmailVO> emailVO = emsDao.findById(ems_seq);

		return (EmailVO) emailVO.get();
	}

	public EmailVO findBySeq(long ems_seq) {

		Optional<EmailVO> emailVO =emsDao.findById(ems_seq);
		return (EmailVO) emailVO.get();
		
//		EmailVO emailVO = emsDao.findByEmsSeq(ems_seq);
//
//		return emailVO;
		
	}

	public EmailVO update(EmailVO emailVO) {

		// insert 실행시 원래 id가 있으면(primary key가 vo에 담겨있으면)
		// update가 되고 데이터가 없으면 insert가 된다.
		emsDao.save(emailVO);

		return emailVO;
	}



}
