package com.ilgusi.request.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilgusi.request.model.dao.RequestDao;
import com.ilgusi.request.model.vo.Request;

@Service
public class RequestService {
	@Autowired
	private RequestDao dao;

	//(문정) 의뢰글 작성 insert
	public int requestInsert(Request req) {
		return dao.requestInsert(req);
	}
	
}
