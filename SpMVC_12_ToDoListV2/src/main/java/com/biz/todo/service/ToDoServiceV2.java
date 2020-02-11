package com.biz.todo.service;

import org.springframework.stereotype.Service;

import com.biz.todo.domain.ToDoList;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service("toServiceV2")
public class ToDoServiceV2 extends ToDoServiceV1 {

	@Override
	public int complete(String str_seq, String td_complete) {

		long td_seq = Long.valueOf(str_seq);

		return toDao.complete(td_seq);
	}

	@Override
	public int alarm(String str_seq, String td_alarm) {
		
		long td_seq = Long.valueOf(str_seq);

		return toDao.alarm(td_seq);
	}

	@Override
	public int update(ToDoList todoList) {
		
		return toDao.update(todoList);
	}

	@Override
	public int delete(long td_seq) {

		log.debug("여기는 서비스V2의 DELETE : " + td_seq);
		
		return toDao.delete(td_seq);
	}

}
