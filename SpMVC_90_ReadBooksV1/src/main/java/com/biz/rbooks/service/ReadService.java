package com.biz.rbooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.rbooks.domain.ReadDTO;
import com.biz.rbooks.repository.ReadDao;

@Service
public class ReadService {

	protected final ReadDao rDao;
	
	@Autowired
	public ReadService(ReadDao rDao) {
		super();
		this.rDao = rDao;
	}

	public List<ReadDTO> selectAll() {

		return rDao.selectAll();
	}

	public ReadDTO findByRBCode(long rb_bcode) {
		// TODO Auto-generated method stub
		return rDao.findByRBCode(rb_bcode);
	}
	
	public List<ReadDTO> findByRbSep(long rb_seq){
		
		return rDao.findByRbSeq(rb_seq);
	}

	public int insert(ReadDTO readDTO) {

		
		return rDao.insert(readDTO);
	}

	public int update(ReadDTO readDTO) {

		
		return  rDao.update(readDTO);
	}

	public ReadDTO delete(long rb_seq) {
		
		return rDao.delete(rb_seq);
	}
	
	


}
