package com.biz.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.rest.domain.RestDTO;
import com.biz.rest.persistence.RestDao;

@Service
public class RestService {

	protected final RestDao rDao;
	protected RestService restService;
	
	@Autowired
	public RestService(RestDao rDao) {
		super();
		this.rDao = rDao;
	}
	
	public List<RestDTO> selectAll() {

		return rDao.selectAll();
	}

	

}
