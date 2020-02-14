package com.biz.rbooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.rbooks.domain.BooksDTO;
import com.biz.rbooks.domain.PageDTO;
import com.biz.rbooks.repository.BookDao;

@Service
public class BookService {

	protected final BookDao bDao;
	protected BookService bService;

	@Autowired
	public BookService(BookDao bDao) {
		super();
		this.bDao = bDao;
	}

	public List<BooksDTO> selectAll() {

		return bDao.selectAll();
	}

	public BooksDTO findByBCode(String b_code) {

		return bDao.findByBCode(b_code);
	}

	public List<BooksDTO> findByBNames(String b_name) {

		return bDao.findByBNames(b_name);
	}

	public int insert(BooksDTO booksDTO) {

		return bDao.insert(booksDTO);
	}

	public int update(BooksDTO booksDTO) {

		return bDao.update(booksDTO);
	}

	public int delete(String b_code) {

		return bDao.delete(b_code);
	}

	public long totalCount() {

		return bDao.proTotalCount();
	}

	public List<BooksDTO> selectPagination(PageDTO pageDTO) {

		List<BooksDTO> booksDTOList = bDao.selectPagination(pageDTO);

		return booksDTOList;

	}
	
	public List<BooksDTO> searchPagination(PageDTO pageDTO, String search) {

		List<BooksDTO> booksDTOList = bDao.searchPagination(pageDTO,search);

		return booksDTOList;

	}
	
	public long searchCount(long bCount) {

		return bDao.searchCount();
	}

}
