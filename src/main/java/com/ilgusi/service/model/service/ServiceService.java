package com.ilgusi.service.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilgusi.member.model.vo.Member;
import com.ilgusi.service.model.dao.ServiceDao;

@Service
public class ServiceService {
	@Autowired
	private ServiceDao dao;

	
}
