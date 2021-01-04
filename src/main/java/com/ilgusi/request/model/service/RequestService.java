package com.ilgusi.request.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilgusi.request.model.dao.RequestDao;

@Service
public class RequestService {
	@Autowired
	private RequestDao dao;
	
}
